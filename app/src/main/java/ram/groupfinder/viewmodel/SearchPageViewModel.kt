package ram.groupfinder.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ram.groupfinder.database.searchPosts
import ram.groupfinder.model.Post
import ram.groupfinder.util.postsFromDocuments
import ram.groupfinder.util.stringToKeywords

class SearchPageViewModel: ViewModel() {

    private val _posts = mutableStateOf(listOf<Post>() )
    val posts: State<List<Post>> = _posts

    private fun onPostsChange(newList: List<Post>) {
        _posts.value = newList
    }
    private val _keywords = mutableStateOf("")
    val keywords: State<String> = _keywords

    fun onKeywordsChange(newValue: String){
        _keywords.value = newValue
    }

    fun getPosts(isGroupSearch: Boolean){
        searchPosts(stringToKeywords(keywords.value), isGroupSearch).addOnCompleteListener { task ->
            val result = task.result
            if(task.isSuccessful){
                onPostsChange(postsFromDocuments(result.documents))
            }
        }
    }
}