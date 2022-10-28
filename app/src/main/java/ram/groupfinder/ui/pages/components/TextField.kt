package ram.groupfinder.ui.pages.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ram.groupfinder.ui.theme.GroupFinderTheme

/*
https://www.jetpackcompose.net/textfield-in-jetpack-compose
*/

@Composable
fun TextField (
    labelText: String?,
    placeholderText: String?
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

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
        }
    )


}

@Preview
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        //TextField(label = "PreviewLabel", placeholder = "PreviewPlaceholder")
    }
}