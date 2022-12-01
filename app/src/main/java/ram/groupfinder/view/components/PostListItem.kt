package ram.groupfinder.view.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ram.groupfinder.database.getUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.userFromDocument

@Composable
fun PostListItem(post: Post){
    post.title?.let { Text(text = it) }
    post.description?.let { Text(text = it)}
}
