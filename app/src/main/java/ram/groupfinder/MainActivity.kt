package ram.groupfinder

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ram.groupfinder.model.BottomNavItem
import ram.groupfinder.pages.*
import ram.groupfinder.ui.theme.GroupFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GroupFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    MainScreen()
                }

                }
            }
        }
    }

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun MainScreen() {
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false // Status bar
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(Modifier.fillMaxWidth(), backgroundColor = MaterialTheme.colors.primary) {
                Box {
                    Text(
                        text = "Group Finder",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onPrimary
                    )
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
       backgroundColor = MaterialTheme.colors.primary,
       elevation = 5.dp
   ) {
       items.forEach{ item ->
           val selected = item.route == backStackEntry.value?.destination?.route
           BottomNavigationItem(
               selected = selected,
               onClick = { onItemClick(item) },
               selectedContentColor = MaterialTheme.colors.onPrimary,
               unselectedContentColor = MaterialTheme.colors.background,
               icon = {
                   Column(horizontalAlignment = CenterHorizontally){
                       Icon(imageVector = item.icon, contentDescription = null, modifier = Modifier.size(40.dp))
                       if(selected){
                           Text(
                               text = item.name,
                               textAlign = TextAlign.Center,
                               fontSize = 10.sp,
                               color = MaterialTheme.colors.onPrimary
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
            SearchGroup()
        }
        composable("searchPerson"){
            SearchPerson()
        }
        composable("createPost"){
            CreatePost()
        }
        composable("profile"){
            Profile()
        }
    }
}
