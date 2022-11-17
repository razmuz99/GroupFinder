package ram.groupfinder.ui.pages.my_posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun Profile(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Text(
            text = "Profile Page",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Button(

            onClick = {
                val db = Firebase.firestore
                val user = hashMapOf(
                    "first" to "Ada",
                    "last" to "Lovelace",
                    "born" to 1815
                )

                db.collection("users")
                    .add(user)

            }
        ){
            Text(text = "Test")
        }
    }
}