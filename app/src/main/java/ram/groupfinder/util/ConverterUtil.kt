package ram.groupfinder.util

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import ram.groupfinder.model.Post
import ram.groupfinder.model.User
import java.util.Date

data class FBUser(
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: Number?,
    val image: String?
    )

data class FBPost(
    val title: String?,
    val description: String?,
    val userId: String?,
    val date: Timestamp?,
    val keywords: List<String>?,
    val isGroupPost: Boolean?,
    val picture: String?,
    val location: String?
)

fun stringListToLowercase(list: List<String>): List<String>{

    val newList = mutableListOf<String>()
    for (string: String in list){
        newList.add(string.lowercase())
    }
    return newList.toList()
}

fun userFromFirebaseUser(firebaseUser: FirebaseUser): User {
    return User(
        firebaseUser.uid,
        firebaseUser.email,
        firebaseUser.displayName?.let { getFirstName(it) },
        firebaseUser.displayName?.let { getLastName(it) },
        firebaseUser.phoneNumber?.toInt(),
        firebaseUser.photoUrl.toString()
    )
}

fun userFromDocument(document: DocumentSnapshot): User {
    val data = document.data
    return if (data != null) {
        User(
            document.id,
            data["email"] as String?,
            data["firstName"] as String?,
            data["lastName"] as String?,
            data["phoneNumber"] as Int,
            data["image"] as String?
        )
    }else{
        throw Exception("No user found")
    }
}

fun userToFBUser(user: User): FBUser {
    return FBUser(user.email, user.firstName, user.lastName, user.phoneNumber, user.image)
}

fun postFromDocument(document: DocumentSnapshot): Post {
    val data = document.data
    return if (data != null) {
        Post(
            data["postId"] as String,
            data["title"] as String,
            data["description"] as String,
            data["userId"] as String,
            data["date"] as Timestamp,
            data["tags"] as List<String>,
            data["isGroupPost"] as Boolean,
            data["image"] as String,
            data["location"] as String,
        )
    }else{
        throw Exception("No user found")
    }
}

fun postsFromDocuments(documents: List<DocumentSnapshot>): ArrayList<Post> {
    var posts = arrayListOf<Post>()
    for(document: DocumentSnapshot in documents){
        posts.add(postFromDocument(document))
    }
    return posts
}

fun postToFBPost(post: Post): FBPost{
    return FBPost(post.title, post.description, post.userId, post.date, post.keywords, post.isGroupPost, post.picture, post.location)
}

