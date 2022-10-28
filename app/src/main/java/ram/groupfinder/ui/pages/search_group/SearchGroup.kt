package ram.groupfinder.ui.pages.search_group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import ram.groupfinder.ui.pages.components.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SearchGroup(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Column() {
            Text(
                text = "Search Group Page",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(labelText = "Label", placeholderText = "Placeholder")
        }
    }
}
