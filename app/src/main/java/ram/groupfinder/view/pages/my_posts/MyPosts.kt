package ram.groupfinder.view.pages.my_posts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.view.components.PageTitle
import ram.groupfinder.view.components.PostListItem
import ram.groupfinder.viewmodel.MyPostsViewModel

@Composable
fun MyPosts(){
    val viewModel: MyPostsViewModel = viewModel()
    val fBUser = FirebaseAuth.getInstance().currentUser
    if(fBUser != null){
        viewModel.getMyPosts(fBUser.uid)
    }
    Box(modifier = Modifier.fillMaxSize().padding(bottom = 60.dp), contentAlignment = Alignment.TopCenter){
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            PageTitle(title = "My Posts")
            LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)){
                items(viewModel.myPosts.value) { post ->
                    PostListItem(post = post)
                }
            }
        }
    }
}



