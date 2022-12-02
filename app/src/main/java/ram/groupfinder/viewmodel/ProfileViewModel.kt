package ram.groupfinder.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.getUser
import ram.groupfinder.model.User
import ram.groupfinder.util.userFromDocument
import ram.groupfinder.view.pages.profile.ProfileInfo

class ProfileViewModel: ViewModel() {
    private val _user = mutableStateOf(User("", null, null, null,null,null,))
    val user: State<User> = _user

    fun onUserChange(user: User){
        _user.value = user
    }

    fun getUser(){
        val fbUser = FirebaseAuth.getInstance().currentUser
        if (fbUser != null){
            getUser(fbUser.uid).addOnCompleteListener{ task ->
                val result = task.result
                if(task.isSuccessful){
                    onUserChange(userFromDocument(result))
                }
            }
        }
    }





}