package ram.groupfinder.ui.pages.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ram.groupfinder.ui.theme.GroupFinderTheme

/*
https://www.jetpackcompose.net/textfield-in-jetpack-compose
Labels and placeholders.
*/

/**
 * TextField is a reusable component with optional label and placeholder
 * It is used in SearchBar.kt
 * */
@Composable
fun TextField (
    labelText: String?,
    placeholderText: String?
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    //val keyboardController = LocalSoftwareKeyboardController.current // experimental

    TextField(
        value = text,
        onValueChange = {newText -> text = newText},
        label = {
            if (labelText != null) {
                Text(text = labelText)
            }
        },
        placeholder = {
            if (placeholderText != null) {
                Text(text = placeholderText)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, // permits all types of text
            imeAction = ImeAction.Search // makes a search icon in lower right corner of keyboard
        ),
        /* KeyboardActions(
            onSearch = { TODO: Implement search }
        ) */
    )
}

@Preview
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        //TextField(labelText = "PreviewLabel", placeholderText = "PreviewPlaceholder")
    }
}