package ram.groupfinder.view.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.deleteUser
import ram.groupfinder.util.getFullName
import ram.groupfinder.view.components.PageTitle
import ram.groupfinder.viewmodel.ProfileViewModel

@Composable
fun Profile(navController : NavController, deleteAccount: () -> Unit, signOut: () -> Unit){


    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ){
        Column(modifier = Modifier.fillMaxWidth()) {
            PageTitle(title = "Profile")
            ProfileInfo()
            Options(navController, deleteAccount, signOut)
        }
    }
}
@Composable
fun ProfileInfo(){
    val viewModel: ProfileViewModel = viewModel()
    viewModel.getUser()
    Column(modifier = Modifier.fillMaxWidth()
    ) {
        // Card of the User's profile picture
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(shape = CircleShape,
                modifier = Modifier
                    .padding(10.dp)
                    .size(150.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(viewModel.user.value.image),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
            Column(modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = getFullName(viewModel.user.value), fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                Text(text = if(viewModel.user.value.email != null){viewModel.user.value.email.toString()}else{" "}, fontSize = 15.sp, modifier = Modifier.padding(5.dp))
                Text(text = if(viewModel.user.value.phoneNumber != null){viewModel.user.value.phoneNumber.toString()}else{" "}, fontSize = 15.sp, modifier = Modifier.padding(5.dp))


            }
        }

        // Personal info from User.






    }
}

@Composable
fun Options(navController : NavController, deleteAccount: () -> Unit, signOut: () -> Unit){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp/*, horizontal = 100.dp*/),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Button(onClick = { navController.navigate("myPosts") }, modifier = Modifier.defaultMinSize(minWidth = 175.dp)) {
            Text(text = "View posts")
        }

        /*Button(onClick = { *//*TODO*//* }, modifier = Modifier.defaultMinSize(minWidth = 175.dp)) {
            Text(text = "Edit Information")

        }*/

        Button(

            onClick = {
                deleteAccount()
                signOut()
                FirebaseAuth.getInstance().currentUser?.let { deleteUser(it.uid) }
            }, modifier = Modifier.defaultMinSize(minWidth = 175.dp)
        ){
            Text(text = "Delete account")
        }
    }
}
