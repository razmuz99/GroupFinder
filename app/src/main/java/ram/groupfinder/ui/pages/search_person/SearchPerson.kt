package ram.groupfinder.ui.pages.search_person

import androidx.compose.runtime.Composable
import ram.groupfinder.ui.components.SearchPage

@Composable
fun SearchPerson(){
    SearchPage(
        title = "Search Person Page",
        introduction = "Welcome to the person search page.\n" +
                "To find a new group member near you, choose the area you wish to search for a person in. Then type in the field of interest of your group and relevant keywords to find the perfect new addition for your group.",
        searchInterestIntroText = "What kind of group member are you looking for?",
        searchInterestLabelText = "Field of interest",
        searchInterestPlaceholderText = "\"(E.g clarinet player, band, music)\""
    )
}