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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.deleteUser
import ram.groupfinder.database.getUser
import ram.groupfinder.model.User
import ram.groupfinder.util.getFullName

@Composable
fun Profile(navController : NavController, deleteAccount: () -> Unit, signOut: () -> Unit){
    val fbUser = FirebaseAuth.getInstance().currentUser
    val userID: String
    val user : User
    if (fbUser != null){
        userID = fbUser.uid
    }
    else{
        user = User("", null, null, null,null,null,)
    }



    //FirebaseAuth.getInstance().currentUser != null

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ){
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Profile",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            //ProfileInfo(user)
            Options(navController, deleteAccount, signOut)
        }
    }
}
@Composable
fun ProfileInfo(user: User){

    Column(modifier = Modifier.fillMaxWidth()
    ) {
        // Card of the User's profile picture
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.image),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }

        // Personal info from User.
        Column(modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 60.dp)
            .fillMaxWidth()
        ) {
            Text(text = getFullName(user), textAlign = TextAlign.Left)
            Text(text = if(user.email != null){user.email.toString()}else{"No email provided"})
            Text(text = if(user.phoneNumber != null){user.phoneNumber.toString()}else{"No phone number provided"})

        }





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
