package ram.groupfinder.ui.pages.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ram.groupfinder.ui.pages.components.SearchBar

/**
 * SearchPage uses the reusable SearchBar(),
 * and is a template for any search site.
 * Used in SearchGroup.kt and SearchPerson.kt
 * */
@Composable
fun SearchPage (
    title: String,
    introduction: String,
    searchInterestIntroText: String,
    searchInterestLabelText: String,
    searchInterestPlaceholderText: String
) {
    Column (
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = introduction,
            modifier = Modifier.padding(vertical = 30.dp))

        Text(text = "What is your location?")
        SearchBar(
            labelText = "Location",
            placeholderText = "(Eg. Postal code, city, region or country)"
        )

        Spacer(modifier = Modifier.height(10.dp))

        /* TODO: Search bar for group type should maybe not appear until an area is chosen? */
        Text(text = searchInterestIntroText)
        SearchBar(
            labelText = searchInterestLabelText,
            placeholderText = searchInterestPlaceholderText
        )
    }
}
