package ram.groupfinder.ui.models

import java.util.*

data class Post (
    val postId: String?,
    val title: String?,
    val description: String?,
    val userId: String?,
    val date: Date?,
    val keywords: Array<String>?,
    val isGroupPost: Boolean?,
    val picture: String?,
    val location: String?
)