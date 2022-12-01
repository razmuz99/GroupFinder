package ram.groupfinder.view.pages.profile

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
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.R
import ram.groupfinder.database.deleteUser

@Composable
fun Profile(navController : NavController, deleteAccount: () -> Unit, signOut: () -> Unit){
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
            ProfileImage()
            Options(navController)
            Button(

                onClick = {
                    deleteAccount()
                    signOut()
                    FirebaseAuth.getInstance().currentUser?.let { deleteUser(it.uid) }
                }
            ){
                Text(text = "Delete account")
            }
        }
    }
}
@Composable
fun ProfileImage(){
  //  val private
//    private imageview
    //StoreReference storageReference
    //val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = R.drawable.ic_user
    /*rememberPainter(
    if (imageUri.value.isEmpty())
        R.drawable.ic_user
    else
        imageUri.value
)
     */


    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            /*  Image(
                  painter = painter,
                  contentDescription = null,
                  modifier = Modifier.wrapContentSize(),//.clickable {  },
                  contentScale = ContentScale.Crop
              )*/
        }
        Text(text = "Profile picture missing")

    }

}

@Composable
fun Options(navController : NavController){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { /*TODO*/ },) {
            Text(text = "Log out")
        }
        Button(onClick = { navController.navigate("myPosts") }) {
            Text(text = "View posts")
            //related to composeable("myposts"){ MyPosts()}
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Edit Information")

        }
    }
}
/*
Knapper:
Mine opslag.
Rediger informationer.
Log ud.

profil indhold:
    private Imageview profil billede Fra URL givet google konto log in.
    private string navn.
    private string number.
    - mail.


*/