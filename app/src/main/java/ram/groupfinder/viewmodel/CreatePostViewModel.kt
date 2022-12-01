package ram.groupfinder.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import ram.groupfinder.database.createPost
import ram.groupfinder.model.Post
import ram.groupfinder.util.stringListToLowercase
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date


class CreatePostViewModel: ViewModel() {
    //Title
    private val _title = mutableStateOf("" )
    val title: State<String> = _title

    fun onTitleChange(newText: String) {
        _title.value = newText
    }

    //GroupRadioButton
    private val _isGroup = mutableStateOf(false)
    val isGroup: State<Boolean> = _isGroup

    fun onGroupChange(newValue: Boolean) {
        _isGroup.value = newValue
    }

    //PersonRadioButton
    private val _isMember = mutableStateOf(false)
    val isMember: State<Boolean> = _isMember

    fun onMemberChange(newValue: Boolean) {
        _isMember.value = newValue
    }

    //Keywords
    private val _keywords = mutableStateOf("" )
    val keywords: State<String> = _keywords

    fun onKeywordsChange(newText: String) {
        _keywords.value = newText
    }

    //Location
    private val _location = mutableStateOf("" )
    val location: State<String> = _location

    fun onLocationChange(newText: String) {
        _location.value = newText
    }

    //Description
    private val _description = mutableStateOf("" )
    val description: State<String> = _description

    fun onDescriptionChange(newText: String) {
        _description.value = newText
    }

    fun createPost(): Boolean{
        val keywords = keywords.value.split(", ")
        val post = Post(
            postId = "",
            title = title.value,
            description = description.value,
            userId = FirebaseAuth.getInstance().currentUser?.uid,
            date = Timestamp(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())),
            keywords = stringListToLowercase(keywords),
            isGroupPost = isGroup.value,
            picture = "",
            location = location.value
        )
        return  try {
            createPost(post)
            true
        }catch (e: Exception){
            false
        }
    }

    fun reset(){
        onTitleChange("")
        onGroupChange(false)
        onMemberChange(false)
        onDescriptionChange("")
        onKeywordsChange("")
        onLocationChange("")
    }

    fun valuesAreValid(): Boolean{
        return !(title.value.isBlank() || (!isGroup.value && !isMember.value) || keywords.value.isBlank() || location.value.isBlank() || description.value.isBlank())
    }
}