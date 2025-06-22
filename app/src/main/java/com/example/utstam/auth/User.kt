<<<<<<< HEAD
package com.example.utstam.model

data class User(
    var userId: String? = "",
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = "",
    var password: String? = "",
    var username: String? = ""
)
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
}
>>>>>>> acc7194ab210e7f8706dd898d33d0e467dff669b
