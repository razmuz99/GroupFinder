package ram.groupfinder.view.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ram.groupfinder.view.pages.create_post.CreatePost
import ram.groupfinder.view.pages.my_posts.MyPosts
import ram.groupfinder.view.pages.profile.Profile
import ram.groupfinder.view.pages.search_group.SearchGroup
import ram.groupfinder.view.pages.search_person.SearchPerson

@Composable
fun Navigation(navController: NavHostController, deleteAccount: () -> Unit, signOut: () -> Unit){
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
            MyPosts()
        }
        composable("profile"){
            Profile(navController, deleteAccount, signOut)
        }
    }
}
