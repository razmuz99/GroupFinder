package ram.groupfinder.view.pages.search_person

import androidx.compose.runtime.Composable
import ram.groupfinder.view.components.SearchPage

@Composable
fun SearchPerson(){
    SearchPage(
        title = "Search Person",
        searchInterestIntroText = "What kind of member are you looking for?",
        searchInterestLabelText = "Field of interest",
        searchInterestPlaceholderText = "\"(E.g clarinet player, band, music)\"",
        isGroupSearch = false
    )
}