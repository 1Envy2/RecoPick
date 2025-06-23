package com.example.utstam

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var registerLink: TextView
    private lateinit var backButton: Button
    private lateinit var forgotPasswordLink: TextView

    private var firebaseHelper: FirebaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_login)

            // Hide status bar and action bar
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            supportActionBar?.hide()

            initViews()
            initFirebase()
            setupClickListeners()

            Log.d("LoginActivity", "LoginActivity created successfully")
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error in onCreate: ${e.message}", e)
            Toast.makeText(this, "Error loading login page", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initViews() {
        try {
            emailEditText = findViewById(R.id.emailEditText)
            passwordEditText = findViewById(R.id.passwordEditText)
            signInButton = findViewById(R.id.signInButton)
            registerLink = findViewById(R.id.registerLink)
            backButton = findViewById(R.id.backButton)
            forgotPasswordLink = findViewById(R.id.forgotPasswordLink)
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error initializing views: ${e.message}", e)
            throw e
        }
    }

    private fun initFirebase() {
        try {
            firebaseHelper = FirebaseHelper()
            Log.d("LoginActivity", "Firebase helper initialized")
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error initializing Firebase: ${e.message}", e)
            Toast.makeText(this, "Error connecting to database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        signInButton.setOnClickListener {
            performLogin()
        }

        registerLink.setOnClickListener {
            try {
                startActivity(Intent(this, RegisterActivity::class.java))
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error navigating to register: ${e.message}", e)
                Toast.makeText(this, "Error opening registration", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }

        forgotPasswordLink.setOnClickListener {
            Toast.makeText(this, "Sorry this feature isn't available yet :(", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin() {
        if (firebaseHelper == null) {
            Toast.makeText(this, "Database not available", Toast.LENGTH_SHORT).show()
            return
        }

        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            emailEditText.error = "Email is required"
            return
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password is required"
            return
        }

        // Show loading state
        signInButton.isEnabled = false
        signInButton.text = "Signing In..."

        firebaseHelper?.loginUser(email, password) { success, message, user ->
            runOnUiThread {
                signInButton.isEnabled = true
                signInButton.text = "Sign In"

                if (success) {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                    //  Simpan data user ke SharedPreferences
                    val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    sharedPref.edit()
                        .putString("user_name", "${user?.firstName} ${user?.lastName}")
                        .putString("user_email", user?.email)
                        .putString("user_username", user?.username ?: "")
                        .apply()

                    //  Navigasi ke MainActivity
                    try {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        Log.e("LoginActivity", "Error navigating to main: ${e.message}", e)
                        Toast.makeText(
                            this,
                            "Login successful but navigation failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }
}