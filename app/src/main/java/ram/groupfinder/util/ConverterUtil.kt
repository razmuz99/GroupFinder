package ram.groupfinder.util

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
            data["phoneNumber"] as Int?,
            data["image"] as String?
        )
    }else{
        throw Exception("No user found")
    }
}

fun postFromDocument(document: DocumentSnapshot): Post {
    val data = document.data
    return if (data != null) {
        Post(
            data["postId"] as String,
            data["title"] as String,
            data["description"] as String,
            data["userId"] as String,
            data["date"] as Date,
            data["tags"] as Array<String>,
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

fun userToFBUser(user: User): FBUser {
    return FBUser(user.email, user.firstName, user.lastName, user.phoneNumber, user.image)
}