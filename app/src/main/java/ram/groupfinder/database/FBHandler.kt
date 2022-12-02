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

fun searchResult(userQuery: List<String>, isGroupSearch: Boolean): Task<QuerySnapshot> {
    val user = FirebaseAuth.getInstance().currentUser
    return if(user != null){
        Firebase.firestore.collection("posts")
            .whereEqualTo("groupPost", isGroupSearch)
            .whereNotEqualTo("userId", user.uid)
            .whereArrayContainsAny("keywords", userQuery)
            .get()
    }else{
        Firebase.firestore.collection("posts")
            .whereEqualTo("groupPost", isGroupSearch)
            .whereArrayContainsAny("keywords", userQuery)
            .get()
    }

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



