package ram.groupfinder.ui.nav.models

data class User (
    val userId: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: Integer,
    val image: String // TODO: handle image
)