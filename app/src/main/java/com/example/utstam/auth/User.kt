<<<<<<< HEAD
package com.example.utstam

data class User(
    val userId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "", // Note: In production, never store plain text passwords
    val createdAt: Long = System.currentTimeMillis()
) {
    // Empty constructor for Firebase
    constructor() : this("", "", "", "", "", 0)
=======
package com.example.utstam

data class User(
    val userId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "", // Note: In production, never store plain text passwords
    val createdAt: Long = System.currentTimeMillis()
) {
    // Empty constructor for Firebase
    constructor() : this("", "", "", "", "", 0)
>>>>>>> 392c53d3cce54873d4fe3ec93820967f2d7bfb06
}