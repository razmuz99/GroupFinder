package ram.groupfinder.ui.pages.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Search bar is made of TextField() with a clickable IconButton with search function.
 * It is used in SearchPage.kt
 * */
@Composable
fun SearchBar (
    labelText: String?,
    placeholderText: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            labelText = labelText,
            placeholderText = placeholderText,
            textFieldSize = 0.87f
        )
        IconButton(
            onClick = { /*TODO: Make searchable*/ },
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}