package ram.groupfinder.ui.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.convertTo
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import ram.groupfinder.R

@Composable
fun Profile(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ){
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Profile Page",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            ProfileImage()
            Options()
        }
    }
}
@Composable
fun ProfileImage(){
    //val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = R.drawable.ic_user
    /*rememberPainter(
    if (imageUri.value.isEmpty())
        R.drawable.ic_user
    else
        imageUri.value
)
     */
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            /*  Image(
                  painter = painter,
                  contentDescription = null,
                  modifier = Modifier.wrapContentSize(),//.clickable {  },
                  contentScale = ContentScale.Crop
              )*/
        }
        Text(text = "Profile picture missing")

    }
}

@Composable
fun Options(){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(8.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { /*TODO*/ },) {
            Text(text = "Log out")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "View posts")
            //related to composeable("myposts"){ MyPosts()}
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Edit Information")

        }
    }
}
/*
Knapper:
Mine opslag.
Rediger informationer.
Log ud.

profil indhold:
    - profil billede. (optional)
    - navn.
    - tlf nr.
    - mail.


*/