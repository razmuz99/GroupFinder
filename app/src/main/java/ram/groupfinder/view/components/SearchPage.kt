package ram.groupfinder.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.viewmodel.SearchPageViewModel
import androidx.compose.material.TextField

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
    searchInterestPlaceholderText: String,
    isGroupSearch: Boolean
) {
    val viewModel: SearchPageViewModel = viewModel()
    Column (
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PageTitle(title = title)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = searchInterestIntroText)
        TextField(
            value = viewModel.keywords.value,
            onValueChange = {newText -> viewModel.onKeywordsChange(newText)},
            label = { Text(text = searchInterestLabelText)},
            placeholder = { Text(text = searchInterestPlaceholderText)},
            modifier = Modifier.fillMaxWidth().padding(15.dp, 5.dp)
        )
        Button(
            onClick = {viewModel.getPosts(isGroupSearch)},
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(text = "Get posts")
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp)){
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

