package ram.groupfinder.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ram.groupfinder.view.theme.GroupFinderTheme

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
    placeholderText: String?,
    textFieldSize: Float
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    //val keyboardController = LocalSoftwareKeyboardController.current // experimental

    TextField(
        modifier = Modifier.fillMaxWidth(textFieldSize),
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