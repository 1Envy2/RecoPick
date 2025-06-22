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