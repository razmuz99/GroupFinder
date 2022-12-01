package ram.groupfinder.view.pages.search_group

import androidx.compose.runtime.Composable
import ram.groupfinder.view.components.SearchPage

@Composable
fun SearchGroup(){
    SearchPage(
        title = "Search Group",
        searchInterestIntroText = "What kind of group are you looking for?",
        searchInterestLabelText = "Field of interest",
        searchInterestPlaceholderText = "\"(E.g clarinet, band, music)\"",
        isGroupSearch = true
    )
}
