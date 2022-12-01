package ram.groupfinder.view.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.getPostsByUser
import ram.groupfinder.util.FBUser
import ram.groupfinder.viewmodel.CreatePostViewModel
import ram.groupfinder.viewmodel.SearchPageViewModel

/**
 * SearchPage uses the reusable SearchBar(),
 * and is a template for any search site.
 * Used in SearchGroup.kt and SearchPerson.kt
 * */
@Composable
fun SearchPage (
    title: String,
    searchInterestIntroText: String,
    searchInterestLabelText: String,
    searchInterestPlaceholderText: String
) {
    val viewModel: SearchPageViewModel = viewModel()
    Column (
        modifier = Modifier
            //.verticalScroll(rememberScrollState())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PageTitle(title = title)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = searchInterestIntroText)
        SearchBar(
            labelText = searchInterestLabelText,
            placeholderText = searchInterestPlaceholderText
        )
        Button(
            onClick = {viewModel.getPosts()},
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .paddingFromBaseline(bottom = 80.dp)
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(text = "Get posts")
        }
        Box(modifier = Modifier.fillMaxWidth()){
            val fBUser = FirebaseAuth.getInstance().currentUser
            if(fBUser != null){
                LazyColumn(Modifier.fillMaxWidth()){
                    items(viewModel.posts.value) { post ->
                        PostListItem(post = post)
                    }
                }
            }

        }
    }
}

