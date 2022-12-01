package ram.groupfinder.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ram.groupfinder.database.getPostsByUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.postsFromDocuments

class SearchPageViewModel: ViewModel() {

    private val _posts = mutableStateOf(listOf<Post>() )
    val posts: State<List<Post>> = _posts

    fun onPostsChange(newList: List<Post>) {
        _posts.value = newList
    }

    fun getPosts(){
        val _task = getPostsByUser("UKFRNKEykqYTmIJ8z9RrcTw8lG82")
        _task.addOnCompleteListener { task ->
            val result = task.result
            if(task.isSuccessful){
                onPostsChange(postsFromDocuments(result.documents))
            }
        }
    }
}