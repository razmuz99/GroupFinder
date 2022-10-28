package ram.groupfinder.ui.pages.search_group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ram.groupfinder.ui.pages.components.SearchBar

@Composable
fun SearchGroup(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Column {
            Text(
                text = "Search Group Page",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Search for groups of you interest in your area")
            SearchBar(
                labelText = "Location",
                placeholderText = "(Eg. Postal code, city, region or country)"
            )
            /* TODO: Search bar for group type should not appear until an area is chosen? */
            Text(text = "Search for groups from you field of interest")
            SearchBar(
                labelText = "What are you looking for?",
                placeholderText = "(E.g clarinet, woodwind instruments, band or music)"
            )
        }
    }
}
