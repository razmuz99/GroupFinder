package ram.groupfinder.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.createUser
import ram.groupfinder.database.isAuthorised
import ram.groupfinder.database.*
import ram.groupfinder.view.nav.BottomNavigationBar
import ram.groupfinder.view.nav.Navigation
import ram.groupfinder.model.BottomNavItem
import ram.groupfinder.model.User
import ram.groupfinder.util.postsFromDocuments
import ram.groupfinder.util.userFromDocument
import ram.groupfinder.view.theme.GroupFinderTheme
import ram.groupfinder.util.userFromFirebaseUser
import ram.groupfinder.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val fbUser = FirebaseAuth.getInstance().currentUser
            if(fbUser != null){
                getUser(fbUser.uid).addOnCompleteListener { task ->
                    if(task.isComplete && task.result.exists()){
                        viewModel.onTextChange("Log out")
                        Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show()
                    }else{
                        try{
                            createUser(userFromFirebaseUser(fbUser))
                            viewModel.onTextChange("Log out")
                            Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show()
                        }
                        catch (e: Exception){
                            viewModel.onTextChange("Log in")
                            Toast.makeText(this, "Sign in failed", Toast.LENGTH_LONG).show()
                            signOut()
                        }
                    }
                }
            }else{
                viewModel.onTextChange("Log in")
                Toast.makeText(this, "Sign in failed", Toast.LENGTH_LONG).show()
                signOut()
            }
        } else {
            //Failed sign in
            viewModel.onTextChange("Log in")
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                viewModel.onTextChange("Log in")
                Toast.makeText(this, "You are signed out", Toast.LENGTH_LONG).show()
            }
    }

    private fun delete() {
        AuthUI.getInstance()
            .delete(this)
            .addOnCompleteListener {
                viewModel.onTextChange("Log in")
                Toast.makeText(this, "GroupFinder account deleted", Toast.LENGTH_LONG).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GroupFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(createSignInIntent = ::createSignInIntent, signOut = ::signOut, viewModel = viewModel, deleteAccount = ::delete)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MainScreen(createSignInIntent: () -> Unit, signOut: () -> Unit, viewModel: MainViewModel, deleteAccount: () -> Unit) {
    val context = LocalContext.current
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = true
    systemUiController.setStatusBarColor(MaterialTheme.colors.primary)
    systemUiController.isNavigationBarVisible = true
    systemUiController.setNavigationBarColor(Color.Black)
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(Modifier.fillMaxWidth(), backgroundColor = MaterialTheme.colors.primary) {
                Box(Modifier.fillMaxWidth()){
                    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                        Box {
                            Text(
                                text = "Group Finder",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                        Arrangement.SpaceEvenly
                        Box{
                            Button(
                                onClick = {
                                    if(!isAuthorised()){
                                        createSignInIntent()
                                    }else{
                                        navController.navigate("searchGroup")
                                        signOut()
                                    }
                                }
                            ){
                                Text(text = viewModel.logInButtonText.value)
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem(
                    name = "Search Group",
                    route = "searchGroup",
                    icon = Icons.Default.Groups
                ),
                BottomNavItem(
                    name = "Search Person",
                    route = "searchPerson",
                    icon = Icons.Default.Person
                ),
                BottomNavItem(
                    name = "Create Post",
                    route = "createPost",
                    icon = Icons.Default.Create
                ),
                BottomNavItem(
                    name = "Profile",
                    route = "profile",
                    icon = Icons.Default.LibraryBooks
                )

            ),
                navController = navController,
                onItemClick = {
                    if((it.route == "profile" || it.route == "createPost") && !isAuthorised()){
                        Toast.makeText(context, "Log in to view this page", Toast.LENGTH_LONG).show()
                    }else{
                        navController.navigate(it.route)
                    }

                }
            )
        }
    ) {
        Navigation(navController = navController, deleteAccount = deleteAccount, signOut = signOut)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        MainScreen({ }, { }, MainViewModel(), {})
    }
}




