package ram.groupfinder.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ram.groupfinder.database.getPostsByUser
import ram.groupfinder.model.Post
import ram.groupfinder.util.postsFromDocuments

class MyPostsViewModel : ViewModel() {

    private val _myPosts = mutableStateOf(listOf<Post>() )
    val myPosts: State<List<Post>> = _myPosts

    fun onMyPostsChange(newList: List<Post>){
        _myPosts.value = newList
    }

    fun getMyPosts(){
        val _task = getPostsByUser("asfsa")
        _task.addOnCompleteListener { task ->
            val result = task.result
            if(task.isSuccessful){
                onMyPostsChange(postsFromDocuments(result.documents))
            }
        }
    }
}

/*
    Myposts består af, en liste af posts tilhørende den user der er logget ind.

 */