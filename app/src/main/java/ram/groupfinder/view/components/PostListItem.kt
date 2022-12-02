package ram.groupfinder.view.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.getUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.userFromDocument
import ram.groupfinder.view.theme.DarkGreen10
import ram.groupfinder.view.theme.Green10
import ram.groupfinder.view.theme.GreenGrey30
import ram.groupfinder.view.theme.Shapes
import java.util.Date

var title = ""
var date = ""
var location = ""
var desc = ""

@Composable
fun PostListItem(post: Post){
    //post.title?.let { Text(text = it) }
    //post.description?.let { Text(text = it)}

    var contactPhone = ""
    var contactEmail = ""
    Text(text = contactPhone)
    Text(text = contactEmail)


    Text(text = title, fontSize = 30.sp)
    Text(text = date)
    Text(text = location)
    desc = Text(text = desc).toString()

    var descrip = post.description?.let { Text(text = it)}
    //Spacer(modifier = Modifier.height(10.dp))

    /*Card(modifier = Modifier.fillMaxSize()) {
        Column() {
            Text(text = "New card")
            post.description?.let {
                desc = "Description1:\n$it" }
            Text(text = "Still new card")
            descrip
            post.description?.let { "Description2:\n$it" }
        }
    }*/
    Card (modifier = Modifier
        .fillMaxSize()
        .padding(2.dp)
        //.border(width = 4.dp, color = Color.Black, shape = Shapes.medium)
        //.clickable(onClick = )
        .border(2.dp, Green10)
        //.background(color = GreenGrey30)

    ) {
        Column(modifier = Modifier, Arrangement.SpaceBetween) {
            getUser(post.userId!!).addOnCompleteListener { Task ->
                if(Task.isSuccessful) {
                    val user = userFromDocument(Task.result)

                    post.title?.let { title = "YAY" + it }
                    post.date?.let { date = "YAYDate: $it" }

                    post.location?.let {
                        location = "YAYLocation: $it" }

                    post.description?.let {
                        desc = "YAYDescription:\n $it" }
                
                    if (FirebaseAuth.getInstance().currentUser != null) {
                        contactPhone = "Phonenumber: " + user.phoneNumber
                        contactEmail = "Phonenumber: " + user.phoneNumber
                    }
                }
            }
        }
    }
    //post.description?.let { Text(text = it)}
}

// .paddingFromBaseline(bottom = 80.dp)