package ram.groupfinder.ui.pages.components

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ram.groupfinder.ui.theme.GroupFinderTheme

/*
https://www.jetpackcompose.net/textfield-in-jetpack-compose
*/

@Composable
fun TextField () {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    androidx.compose.material.TextField(value = text, onValueChange = {newText -> text = newText})


}

@Preview
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        TextField()
    }
}