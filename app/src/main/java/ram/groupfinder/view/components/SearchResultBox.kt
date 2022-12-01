package ram.groupfinder.view.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ram.groupfinder.view.theme.GroupFinderTheme

@Composable
fun SearchResultBox (resultList: List<String>) {

    LazyColumn {
        items(resultList) {
            Text(text = it)
        }
    }
}

val testList = listOf("en", "to", "tre")

@Preview
@Composable
fun SearchResultBoxPreview() {
    GroupFinderTheme {
        SearchResultBox(resultList = testList)
    }
}