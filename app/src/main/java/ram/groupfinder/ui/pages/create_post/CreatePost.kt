package ram.groupfinder.ui.pages.create_post

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ram.groupfinder.ui.pages.components.PageTitle
import ram.groupfinder.ui.pages.components.SearchBar
import ram.groupfinder.ui.pages.components.TextField

@Composable
fun CreatePost(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PageTitle(title = "Create Post Page")

        /* TODO: Checkboxes whether user is in a group or not */
        TextField(labelText = "Title", placeholderText = "E.g. Searching for band member", textFieldSize = 0.87f)
        SearchBar(labelText = "Keywords", placeholderText = "E.g. xylophone, evenings, mondays") /* TODO: Each keyword should be listed when chosen */
        SearchBar(labelText = "Location", placeholderText = "E.g. Birkum, Odense S, Fyn")
        TextField(labelText = "Description", placeholderText = "Describe who you are looking for", textFieldSize = 0.87f)
    }
}