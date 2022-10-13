package ram.groupfinder

import android.graphics.drawable.Drawable
import android.location.GnssAntennaInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ram.groupfinder.ui.theme.GroupFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroupFinderTheme {
                    MainScreen()
                }
            }
        }
    }


@Composable
private fun MainScreen(/* onButtonClick: () -> Unit */) {
    TopAppBar(Modifier.fillMaxWidth()) {
        Box() {
            Text(
                text = "Top bar",
                fontSize = 20.sp
            )
            IconButton(onClick = { /*TODO*/ }) {
               // Icon(addPathNodes(res/drawable/Beta_logo.png))

            }
        }
    }
    //Top
    Box(modifier = Modifier.fillMaxSize(),  contentAlignment = Alignment.Center){
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hello there, how are u?",
                fontSize = 32.sp,
                textAlign = TextAlign.Center

            )
            
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        MainScreen()
    }
}