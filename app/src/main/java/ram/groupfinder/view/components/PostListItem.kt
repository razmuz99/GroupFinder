package ram.groupfinder.view.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ram.groupfinder.model.Post
import ram.groupfinder.view.theme.*

@Composable
fun PostListItem(post: Post) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .border(2.dp, MaterialTheme.colors.primary, Shapes.medium),
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity) // Same as textField
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            Arrangement.SpaceBetween
        ) {
            post.title?.let { Text(
                text = it,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold) }
            post.location?.let { Text(text = "Location: $it") }
            post.description?.let { Text(text = "Description:\n$it") }

                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Contact information:", fontWeight = FontWeight.Bold)
                var contactPhone = Text(text = "Phonenumber: " + "")
                var contactEmail = Text(text = "Email: " + "")


            post.date?.let { Text(
                text = "Posted: ${it.toDate()}",
                fontSize = 10.sp,
                modifier = Modifier.align(alignment = Alignment.End)
            ) }
        }
    }
}