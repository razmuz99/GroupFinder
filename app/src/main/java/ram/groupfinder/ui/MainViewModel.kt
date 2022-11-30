package ram.groupfinder.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel : ViewModel(){
    private val _text = mutableStateOf(if(FirebaseAuth.getInstance().currentUser == null){"Log in"}else{"Log out"} )
    val text: State<String> = _text

    fun onTextChange(newText: String){
        _text.value = newText
    }
}