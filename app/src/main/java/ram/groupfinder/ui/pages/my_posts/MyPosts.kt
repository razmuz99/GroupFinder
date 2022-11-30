package ram.groupfinder.ui.pages.my_posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.ui.database.deleteUser

@Composable
fun MyPosts(deleteAccount: () -> Unit, signOut: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Text(
            text = "My Posts Page",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
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