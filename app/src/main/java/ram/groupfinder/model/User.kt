package ram.groupfinder.model

data class User (
    val userId: String,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: Number?,
    val image: String?
)