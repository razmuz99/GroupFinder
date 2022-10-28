package ram.groupfinder.ui.pages.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import ram.groupfinder.ui.pages.components.TextField

@Composable
fun SearchBar (
    labelText: String?,
    placeholderText: String?
) {
    Row() {
        TextField(
            labelText = labelText,
            placeholderText = placeholderText)

        /* TODO: Possibly add iconButton with search ability */

    }
}