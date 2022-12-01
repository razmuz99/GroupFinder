package ram.groupfinder.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel(){
    private val _logInButtonText = mutableStateOf(if(FirebaseAuth.getInstance().currentUser == null){"Log in"}else{"Log out"} )
    val logInButtonText: State<String> = _logInButtonText

    fun onTextChange(newText: String){
        _logInButtonText.value = newText
    }
}