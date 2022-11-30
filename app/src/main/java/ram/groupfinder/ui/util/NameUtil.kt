package ram.groupfinder.ui.util

fun getFirstName(name: String): String{
    return name.split(" ")[0]
}
fun getLastName(name: String): String{
    val nameArray = name.split(" ")
    var lastName = ""
    for (nameSegment in nameArray.drop(1)){
        lastName += "$nameSegment "
    }
    return lastName.dropLast(1)
}