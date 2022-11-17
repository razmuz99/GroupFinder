package ram.groupfinder.ui.nav.models

import java.util.*

data class Post (
    val title: String,
    val description: String,
    val user: String, // /* TODO: get user ID */
    val date: Date, // /* TODO: handle date of creation */
    val keywords: Array<String>,
    val isGroupPost: Boolean,
    val picture: String?, /* TODO: make picture possible */
    val location: String /* TODO: make location possible */
)