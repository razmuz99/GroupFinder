package ram.groupfinder.view.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.getUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.userFromDocument
import ram.groupfinder.view.theme.*

@Composable
fun PostListItem(post: Post) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            //.clickable(onClick = )
            .border(2.dp, MaterialTheme.colors.primary, Shapes.medium),
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity) // Same as textField
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                post.title?.let { Text(text = it, fontSize = 23.sp, fontWeight = FontWeight.Bold) }
                post.date?.let { Text(text = "Posted: ${it.toDate()}", fontSize = 10.sp) }
            }
            post.location?.let { Text(text = "Location: $it") }
            post.description?.let { Text(text = "Description:\n$it") }

            //if (FirebaseAuth.getInstance().currentUser != null) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Contact information:", fontWeight = FontWeight.Bold)
                var contactPhone = Text(text = "Phonenumber: " + "")
                var contactEmail = Text(text = "Email: " + "")
            //}
        }
    }
}