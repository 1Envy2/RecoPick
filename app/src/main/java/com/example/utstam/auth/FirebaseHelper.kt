package com.example.utstam

import com.google.firebase.database.*
import java.security.MessageDigest
import android.os.Handler
import android.os.Looper
import android.util.Log

class FirebaseHelper {
    private val database: FirebaseDatabase
    private val usersRef: DatabaseReference
    private val timeoutHandler = Handler(Looper.getMainLooper())

    // Set timeout untuk operasi database (10 detik)
    private val TIMEOUT_DURATION = 10000L

    init {
        try {
            database = FirebaseDatabase.getInstance()
            usersRef = database.getReference("users")

            // Set offline capabilities (hanya sekali)
            try {
                database.setPersistenceEnabled(true)
            } catch (e: Exception) {
                Log.w("FirebaseHelper", "Persistence already enabled: ${e.message}")
            }

            usersRef.keepSynced(true)
            Log.d("FirebaseHelper", "Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e("FirebaseHelper", "Error initializing Firebase: ${e.message}")
            throw e
        }
    }

    // Hash password for security
    private fun hashPassword(password: String): String {
        return try {
            val bytes = password.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            digest.fold("", { str, it -> str + "%02x".format(it) })
        } catch (e: Exception) {
            Log.e("FirebaseHelper", "Error hashing password: ${e.message}")
            password // Fallback to plain text (not recommended for production)
        }
    }

    // Register new user dengan timeout
    fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        Log.d("FirebaseHelper", "Starting registration for: $email")

        var isCallbackCalled = false

        // Set timeout
        val timeoutRunnable = Runnable {
            if (!isCallbackCalled) {
                isCallbackCalled = true
                Log.w("FirebaseHelper", "Registration timeout for: $email")
                callback(false, "Registration timeout. Please check your internet connection.")
            }
        }
        timeoutHandler.postDelayed(timeoutRunnable, TIMEOUT_DURATION)

        try {
            // Check if email already exists
            usersRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (isCallbackCalled) return

                        try {
                            timeoutHandler.removeCallbacks(timeoutRunnable)

                            if (snapshot.exists()) {
                                isCallbackCalled = true
                                Log.d("FirebaseHelper", "Email already exists: $email")
                                callback(false, "Email already registered")
                            } else {
                                // Create new user
                                val userId = usersRef.push().key
                                if (userId == null) {
                                    isCallbackCalled = true
                                    Log.e("FirebaseHelper", "Failed to generate user ID")
                                    callback(false, "Failed to generate user ID")
                                    return
                                }

                                val hashedPassword = hashPassword(password)

                                val user = User(
                                    userId = userId,
                                    firstName = firstName,
                                    lastName = lastName,
                                    email = email,
                                    password = hashedPassword
                                )

                                // Set timeout untuk write operation
                                val writeTimeoutRunnable = Runnable {
                                    if (!isCallbackCalled) {
                                        isCallbackCalled = true
                                        Log.w("FirebaseHelper", "Write timeout for: $email")
                                        callback(false, "Registration timeout. Please try again.")
                                    }
                                }
                                timeoutHandler.postDelayed(writeTimeoutRunnable, TIMEOUT_DURATION)

                                usersRef.child(userId).setValue(user)
                                    .addOnSuccessListener {
                                        timeoutHandler.removeCallbacks(writeTimeoutRunnable)
                                        if (!isCallbackCalled) {
                                            isCallbackCalled = true
                                            Log.d("FirebaseHelper", "Registration successful for: $email")
                                            callback(true, "Registration successful")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        timeoutHandler.removeCallbacks(writeTimeoutRunnable)
                                        if (!isCallbackCalled) {
                                            isCallbackCalled = true
                                            Log.e("FirebaseHelper", "Registration failed for: $email", exception)
                                            callback(false, "Registration failed: ${exception.message}")
                                        }
                                    }
                            }
                        } catch (e: Exception) {
                            if (!isCallbackCalled) {
                                isCallbackCalled = true
                                Log.e("FirebaseHelper", "Error in onDataChange: ${e.message}", e)
                                callback(false, "Registration error: ${e.message}")
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        timeoutHandler.removeCallbacks(timeoutRunnable)
                        if (!isCallbackCalled) {
                            isCallbackCalled = true
                            Log.e("FirebaseHelper", "Database cancelled: ${error.message}")
                            callback(false, "Database error: ${error.message}")
                        }
                    }
                })
        } catch (e: Exception) {
            timeoutHandler.removeCallbacks(timeoutRunnable)
            if (!isCallbackCalled) {
                isCallbackCalled = true
                Log.e("FirebaseHelper", "Error starting registration: ${e.message}", e)
                callback(false, "Registration error: ${e.message}")
            }
        }
    }

    // Login user dengan timeout
    fun loginUser(
        email: String,
        password: String,
        callback: (Boolean, String, User?) -> Unit
    ) {
        Log.d("FirebaseHelper", "Starting login for: $email")

        var isCallbackCalled = false
        val hashedPassword = hashPassword(password)

        // Set timeout
        val timeoutRunnable = Runnable {
            if (!isCallbackCalled) {
                isCallbackCalled = true
                Log.w("FirebaseHelper", "Login timeout for: $email")
                callback(false, "Login timeout. Please check your internet connection.", null)
            }
        }
        timeoutHandler.postDelayed(timeoutRunnable, TIMEOUT_DURATION)

        try {
            usersRef.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (isCallbackCalled) return

                        try {
                            timeoutHandler.removeCallbacks(timeoutRunnable)

                            if (snapshot.exists()) {
                                for (userSnapshot in snapshot.children) {
                                    val user = userSnapshot.getValue(User::class.java)
                                    if (user != null && user.password == hashedPassword) {
                                        isCallbackCalled = true
                                        Log.d("FirebaseHelper", "Login successful for: $email")
                                        callback(true, "Login successful", user)
                                        return
                                    }
                                }
                                isCallbackCalled = true
                                Log.d("FirebaseHelper", "Invalid password for: $email")
                                callback(false, "Invalid password", null)
                            } else {
                                isCallbackCalled = true
                                Log.d("FirebaseHelper", "Email not found: $email")
                                callback(false, "Email not found", null)
                            }
                        } catch (e: Exception) {
                            if (!isCallbackCalled) {
                                isCallbackCalled = true
                                Log.e("FirebaseHelper", "Error in login onDataChange: ${e.message}", e)
                                callback(false, "Login error: ${e.message}", null)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        timeoutHandler.removeCallbacks(timeoutRunnable)
                        if (!isCallbackCalled) {
                            isCallbackCalled = true
                            Log.e("FirebaseHelper", "Login cancelled: ${error.message}")
                            callback(false, "Database error: ${error.message}", null)
                        }
                    }
                })
        } catch (e: Exception) {
            timeoutHandler.removeCallbacks(timeoutRunnable)
            if (!isCallbackCalled) {
                isCallbackCalled = true
                Log.e("FirebaseHelper", "Error starting login: ${e.message}", e)
                callback(false, "Login error: ${e.message}", null)
            }
        }
    }
}