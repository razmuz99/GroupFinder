package ram.groupfinder.view.pages.search_group

import androidx.compose.runtime.Composable
import ram.groupfinder.view.components.SearchPage

@Composable
fun SearchGroup(){
    SearchPage(
        title = "Search Group Page",
        introduction = "Welcome to the group search page.\n" +
                "To find relevant groups near you, choose the area you wish to search for groups in. Then type in your field of interest and relevant keywords to find the perfect group for you.",
        searchInterestIntroText = "What kind of group are you looking for?",
        searchInterestLabelText = "Field of interest",
        searchInterestPlaceholderText = "\"(E.g clarinet, band, music)\""
    )
}
