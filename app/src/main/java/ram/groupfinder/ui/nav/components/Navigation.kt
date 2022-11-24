package ram.groupfinder.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ram.groupfinder.ui.pages.create_post.CreatePost
import ram.groupfinder.ui.pages.my_posts.Profile
import ram.groupfinder.ui.pages.search_group.SearchGroup
import ram.groupfinder.ui.pages.search_person.SearchPerson

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
        composable("myPosts"){
            Profile()
        }
    }
}
