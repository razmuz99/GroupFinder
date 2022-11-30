package ram.groupfinder.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.ui.database.createUser
import ram.groupfinder.ui.database.isAuthorised
import ram.groupfinder.ui.database.userExists
import ram.groupfinder.ui.nav.BottomNavigationBar
import ram.groupfinder.ui.nav.Navigation
import ram.groupfinder.ui.models.BottomNavItem
import ram.groupfinder.ui.theme.GroupFinderTheme
import ram.groupfinder.ui.util.userFromFirebaseUser

class MainActivity : ComponentActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                if(userExists(user.uid)) {
                    Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show()
                }else{
                    createUser(userFromFirebaseUser(user))
                }
            }
        } else {
            //Failed sign in
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
            }
    }

    private fun delete() {
        AuthUI.getInstance()
            .delete(this)
            .addOnCompleteListener {
                // ...
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
                    MainScreen(createSignInIntent = ::createSignInIntent, signOut = ::signOut)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MainScreen(createSignInIntent: () -> Unit, signOut: () -> Unit) {
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

                    val initText = if(FirebaseAuth.getInstance().currentUser == null){
                        "Sign in"
                    }else{
                        "Sign out"
                    }
                    val buttonText = remember {
                        mutableStateOf(initText)
                    }
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
                                        signOut()
                                    }
                                }
                            ){
                                Text(text = buttonText.value)
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
                    name = "My Posts",
                    route = "myPosts",
                    icon = Icons.Default.LibraryBooks
                )

            ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Navigation(navController = navController)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        MainScreen({ }, { })
    }
}




