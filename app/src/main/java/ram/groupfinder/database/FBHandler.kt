package ram.groupfinder.database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ram.groupfinder.model.Post
import ram.groupfinder.model.User
import ram.groupfinder.util.postFromDocument
import ram.groupfinder.util.postsFromDocuments
import ram.groupfinder.util.userFromDocument
import ram.groupfinder.util.userToFBUser

fun userExists(userId : String): Boolean{
    var returnValue = false
    Firebase.firestore.collection("users").document(userId).get().addOnCompleteListener {  task ->
        returnValue = if (task.isSuccessful) {
            val document = task.result
            document?.exists() ?: false
        } else {
            if(task.isComplete){
                false
            }else{
                throw Exception(task.exception)
            }
        }
    }
    return returnValue
}

fun getUser(userId : String): User {
    var user = User(userId, null, null, null, null, null)
    Firebase.firestore.collection("users").document(userId).get().addOnCompleteListener {  task ->
        if (task.isSuccessful) {
            val document = task.result
            user = userFromDocument(document)

        } else {
            //Error
            throw Exception(task.exception)
        }
    }
    return user
}

fun editUser(user: User){
    if(userExists(user.userId)){
        createUser(user)
    }else{
        throw Exception("User does not exist")
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

fun postExists(postId: String): Boolean{
    var returnValue = false
    Firebase.firestore.collection("posts").document(postId).get().addOnCompleteListener {  task ->
        returnValue = if (task.isSuccessful) {
            val document = task.result
            document?.exists() ?: false
        } else {
            if(task.isComplete){
                false
            }else{
                throw Exception(task.exception)
            }
        }
    }
    return returnValue
}

fun getPost(postId: String): Post {
    var post = Post(null, null, null, null, null, null, null, null, null)
    Firebase.firestore.collection("posts").document(postId).get().addOnCompleteListener {  task ->
        if (task.isSuccessful) {
            val document = task.result
            post = postFromDocument(document)

        } else {
            //Error
            throw Exception(task.exception)
        }
    }
    return post
}

fun getPostsByUser(userId: String): ArrayList<Post>{
    var posts = arrayListOf<Post>()
    Firebase.firestore.collection("posts").whereEqualTo("userId", userId).get().addOnCompleteListener { task ->
        if(task.isSuccessful){
            posts = postsFromDocuments(task.result.documents)
        }
    }
    return posts
}

fun createPost(post: Post){
    Firebase.firestore.collection("posts").document().set(post).addOnCompleteListener { task ->
        if(!task.isSuccessful){
            throw Exception(task.exception)
        }
    }
}

fun editPost(post: Post){
    if(post.postId != null){
        if(postExists(post.postId)){
            createPost(post)
        }else{
            throw Exception("Post does not exist")
        }
    }else{
        throw Exception("Missing postId")
    }

}

fun deletePost(postId: String){
    Firebase.firestore.collection("posts").document(postId).delete()
}

fun isAuthorised(): Boolean{
    return FirebaseAuth.getInstance().currentUser != null
}

fun searchResult(field: String, userQuery: String) {
    val searchPost = Firebase.firestore.collection("posts")
    val searchPostRef = Firebase.firestore.collection("posts")
    val querySearch = searchPostRef.whereEqualTo(field, userQuery)

    searchPostRef.whereArrayContainsAny(field, listOf(userQuery))

/*val cities = db.collection("cities")
// Create a reference to the cities collection
val citiesRef = db.collection("cities")
// Create a query against the collection.
val query = citiesRef.whereEqualTo("location", "CA")

db.collection("cities")
    .whereEqualTo("capital", true)
    .get()
    .addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }*/
    /*Firebase.firestore.collection("posts")
    .whereEqualTo("title", true)
    .get()
    .addOnSuccessListener*/
}

