package ram.groupfinder.ui.pages.create_post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ram.groupfinder.ui.components.PageTitle
import ram.groupfinder.ui.components.SearchBar
import ram.groupfinder.ui.components.TextField

@Composable
fun CreatePost(){
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PageTitle(title = "Create Post Page")

        Text(
            text = "Create a new post describing what type of group you have or are looking to create. Make a good description and use specific keywords to get the best results. Your next group adventure starts now!",
            modifier = Modifier.padding(vertical = 30.dp))

        TextField(
            labelText = "Title",
            placeholderText = "E.g. Searching for band member",
            textFieldSize = 0.87f)

        val isChecked = false
        val checkedStateGroup = remember { mutableStateOf(isChecked) }
        val checkedStatePerson = remember { mutableStateOf(isChecked) }
        Text(text = "Are you searching for a whole group or a single person?")
        Row {
            Checkbox(checked = checkedStateGroup.value,
                onCheckedChange = {
                    if (it) {
                        checkedStateGroup.value = it
                        checkedStatePerson.value = !it
                    }
                })
            Text(text = "Group", modifier = Modifier.padding(12.dp))
            Checkbox(checked = checkedStatePerson.value,
                onCheckedChange = {
                    if (it) {
                        checkedStatePerson.value = it
                        checkedStateGroup.value = !it
                    }
                })
            Text(text = "Person", modifier = Modifier.padding(12.dp))
        }

        SearchBar(
            labelText = "Keywords",
            placeholderText = "E.g. xylophone, evenings, mondays") /* TODO: Each keyword should be listed when chosen */

        SearchBar(
            labelText = "Location",
            placeholderText = "E.g. Birkum, Odense S, Fyn")

        TextField(
            labelText = "Description",
            placeholderText = "Describe who you are looking for",
            textFieldSize = 0.87f)

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .paddingFromBaseline(bottom = 80.dp)
        ) {
            Text(text = "Create post")
        }
    }
}