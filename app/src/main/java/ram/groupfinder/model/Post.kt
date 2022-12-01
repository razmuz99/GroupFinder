package ram.groupfinder.model

import java.util.*
import com.google.firebase.Timestamp

data class Post (
    val postId: String?,
    val title: String?,
    val description: String?,
    val userId: String?,
    val date: Timestamp?,
    val keywords: List<String>?,
    val isGroupPost: Boolean?,
    val picture: String?,
    val location: String?
)