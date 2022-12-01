package ram.groupfinder.view.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import ram.groupfinder.database.getUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.userFromDocument

@Composable
fun PostListItem(post: Post){
    post.title?.let { Text(text = it) }
    post.description?.let { Text(text = it)}

    getUser(post.userId!!).addOnCompleteListener { Task ->
        if(Task.isSuccessful) {
            var user = userFromDocument(Task.result)



        }
    }
    post.title?.let {
        Text(text = it,
            fontSize = 10.sp
    ) }
}