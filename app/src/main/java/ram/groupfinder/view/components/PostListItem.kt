package ram.groupfinder.view.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import ram.groupfinder.database.getUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.userFromDocument
import ram.groupfinder.view.theme.DarkGreen10
import ram.groupfinder.view.theme.Green10
import ram.groupfinder.view.theme.GreenGrey30
import ram.groupfinder.view.theme.Shapes
import java.util.Date

@Composable
fun PostListItem(post: Post){
    post.title?.let { Text(text = it) }
    post.description?.let { Text(text = it)}

    /*getUser(post.userId!!).addOnCompleteListener { Task ->
        if(Task.isSuccessful) {
            var user = userFromDocument(Task.result)

            PostListItemLayout(post = post)


        }
    }*/

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        //.border(width = 4.dp, color = Color.Black, shape = Shapes.medium)
        //.clickable(onClick = )
        .border(2.dp, Green10)
        .background(color = GreenGrey30)

    ) {
        Row(modifier = Modifier, Arrangement.SpaceBetween) {
            post.title?.let {
                Text(text = it,
                    fontSize = 30.sp
                )
            }
            post.date?.let { Text(text = "Date: $it") }
        }
        post.location?.let {
            Text(text = "Location: $it")}
        Spacer(modifier = Modifier.height(10.dp))
        post.description?.let {
            Text(text = "Description:\n $it")}

    }
}

/*@Composable
fun PostListItemLayout(post: Post){
    post.title?.let {
        Text(text = it,
            fontSize = 10.sp
        ) }
}*/

// .paddingFromBaseline(bottom = 80.dp)