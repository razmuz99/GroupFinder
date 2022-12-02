package ram.groupfinder.database

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ram.groupfinder.model.Post
import ram.groupfinder.model.User
import ram.groupfinder.util.*

fun getUser(userId : String): Task<DocumentSnapshot> {
    return Firebase.firestore.collection("users").document(userId).get()
}

fun editUser(user: User){
    try{
        createUser(user)
    } catch (e: Exception){
        throw e
    }
}

fun searchResult(userQuery: List<String>, isGroupSearch: Boolean): Task<QuerySnapshot> {
    return Firebase.firestore.collection("posts").whereArrayContainsAny("keywords", userQuery).whereEqualTo("groupPost", isGroupSearch).get()
}

fun createUser(user: User){

    Firebase.firestore.collection("users").document(user.userId).set(userToFBUser(user)).addOnCompleteListener { task ->
        if(!task.isSuccessful){
            throw Exception(task.exception)
        }
    }
}

fun deleteUser(userId: String){
    Firebase.firestore.collection("users").document(userId).delete()
}

fun getPost(postId: String): Task<DocumentSnapshot> {
    return Firebase.firestore.collection("posts").document(postId).get()
}

fun getPostsByUser(userId: String): Task<QuerySnapshot> {
    return Firebase.firestore.collection("posts").whereEqualTo("userId", userId).get()
}

fun createPost(post: Post){
    Firebase.firestore.collection("posts").document().set(postToFBPost(post)).addOnCompleteListener { task ->
        if(!task.isSuccessful){
            throw Exception(task.exception)
        }
    }
}

fun deletePost(postId: String){
    Firebase.firestore.collection("posts").document(postId).delete()
}

fun isAuthorised(): Boolean{
    return FirebaseAuth.getInstance().currentUser != null
}



