package ram.groupfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ram.groupfinder.model.BottomNavItem
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
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(Modifier.fillMaxWidth(), backgroundColor = Color.DarkGray) {
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
        },
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem(
                    name = "Search Group",
                    route = "searchGroup",
                    icon = Icons.Default.Groups
                ),
                BottomNavItem(
                    name = "Search Person",
                    route = "searchPerson",
                    icon = Icons.Default.Person
                ),
                BottomNavItem(
                    name = "Create Post",
                    route = "createPost",
                    icon = Icons.Default.Create
                ),
                BottomNavItem(
                    name = "Profile",
                    route = "profile",
                    icon = Icons.Default.AccountCircle
                )

            ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Navigation(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GroupFinderTheme {
        MainScreen()
    }
}
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
   BottomNavigation(
       modifier = modifier,
       backgroundColor = Color.DarkGray,
       elevation = 5.dp
   ) {
       items.forEach{ item ->
           val selected = item.route == backStackEntry.value?.destination?.route
           BottomNavigationItem(
               selected = selected,
               onClick = { onItemClick(item) },
               selectedContentColor = Color.Green,
               unselectedContentColor = Color.Gray,
               icon = {
                   Column(horizontalAlignment = CenterHorizontally){
                       Icon(imageVector = item.icon, contentDescription = null)
                       if(selected){
                           Text(
                               text = item.name,
                               textAlign = TextAlign.Center,
                               fontSize = 10.sp
                           )
                       }
                   }

               }
           )
       }

   }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = "searchGroup"){
        composable("searchGroup"){
            SearchGroupScreen()
        }
        composable("searchPerson"){
            SearchPersonScreen()
        }
        composable("createPost"){
            CreatePostScreen()
        }
        composable("profile"){
            ProfileScreen()
        }
    }
}

@Composable
fun SearchGroupScreen(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Text(text = "Search Group Page")
    }
}
@Composable
fun SearchPersonScreen(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Text(text = "Search Person Page")
    }
}
@Composable
fun CreatePostScreen(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Text(text = "Create Post Page")
    }
}
@Composable
fun ProfileScreen(){
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Text(text = "Profile Page")
    }
}