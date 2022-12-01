package ram.groupfinder.view.pages.create_post

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ram.groupfinder.view.components.PageTitle
import ram.groupfinder.viewmodel.CreatePostViewModel

@Composable
fun CreatePost(){
    val context = LocalContext.current
    val viewModel: CreatePostViewModel = viewModel()
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PageTitle(title = "Create Post")

        TextField(
            value = viewModel.title.value,
            onValueChange = {newText -> viewModel.onTitleChange(newText)},
            label = { Text(text = "Title")},
            placeholder = { Text(text = "E.g. Searching for band member")},
        )

        Text(text = "Are you looking for a group or a member?")
        Row {
            Checkbox(checked = viewModel.isGroup.value,
                onCheckedChange = { newValue ->
                    if (newValue) {
                        viewModel.onGroupChange(true)
                        viewModel.onMemberChange(false)
                    }
                })
            Text(text = "Group", modifier = Modifier.padding(12.dp))
            Checkbox(checked = viewModel.isMember.value,
                onCheckedChange = { newValue ->
                    if (newValue) {
                        viewModel.onMemberChange(true)
                        viewModel.onGroupChange(false)
                    }
                })
            Text(text = "Member", modifier = Modifier.padding(12.dp))
        }

        TextField(
            value = viewModel.keywords.value,
            onValueChange = {newText -> viewModel.onKeywordsChange(newText)},
            label = { Text(text = "Keywords")},
            placeholder = { Text(text = "E.g. xylophone, evenings, mondays")}
        )

        TextField(
            value = viewModel.location.value,
            onValueChange = {newText -> viewModel.onLocationChange(newText)},
            label = { Text(text = "Location")},
            placeholder = { Text(text = "E.g. Birkum, Odense S, Fyn")}
        )

        TextField(
            value = viewModel.description.value,
            onValueChange = {newText -> viewModel.onDescriptionChange(newText)},
            label = { Text(text = "Description")},
            placeholder = { Text(text = "Make a text for your post")},
        )

        Button(
            onClick = {
                if(viewModel.valuesAreValid()) {
                    if (viewModel.createPost()) {
                        Toast.makeText(context, "Post created", Toast.LENGTH_LONG).show()
                        viewModel.reset()
                    } else {
                        Toast.makeText(context, "Failed to create post", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context, "Fields are missing data", Toast.LENGTH_LONG).show()
                }

            },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .paddingFromBaseline(bottom = 80.dp)
        ) {
            Text(text = "Create post")
        }
    }
}