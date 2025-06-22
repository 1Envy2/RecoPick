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

class RegisterActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var loginLink: TextView
    private lateinit var backButton: Button

    private val firebaseHelper = FirebaseHelper()
    private var isRegistering = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Hide status bar and action bar
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signUpButton = findViewById(R.id.signUpButton)
        loginLink = findViewById(R.id.loginLink)
        backButton = findViewById(R.id.backButton)
    }

    private fun setupClickListeners() {
        signUpButton.setOnClickListener {
            if (!isRegistering) {
                performRegistration()
            }
        }

        loginLink.setOnClickListener {
            if (!isRegistering) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        backButton.setOnClickListener {
            if (!isRegistering) {
                finish()
            }
        }
    }

    private fun performRegistration() {
        val firstName = firstNameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validation
        if (firstName.isEmpty()) {
            firstNameEditText.error = "First name is required"
            firstNameEditText.requestFocus()
            return
        }

        if (lastName.isEmpty()) {
            lastNameEditText.error = "Last name is required"
            lastNameEditText.requestFocus()
            return
        }

        if (email.isEmpty()) {
            emailEditText.error = "Email is required"
            emailEditText.requestFocus()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Please enter a valid email"
            emailEditText.requestFocus()
            return
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password is required"
            passwordEditText.requestFocus()
            return
        }

        if (password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters"
            passwordEditText.requestFocus()
            return
        }

        // Show loading state
        setLoadingState(true)

        Log.d("RegisterActivity", "Starting registration for: $email")

        firebaseHelper.registerUser(firstName, lastName, email, password) { success, message ->
            runOnUiThread {
                setLoadingState(false)

                Log.d("RegisterActivity", "Registration result: $success, message: $message")

                if (success) {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    // Navigate to login activity
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("registered_email", email)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setLoadingState(loading: Boolean) {
        isRegistering = loading
        signUpButton.isEnabled = !loading
        signUpButton.text = if (loading) "Signing Up..." else "Sign Up"

        // Disable other buttons during loading
        loginLink.isEnabled = !loading
        backButton.isEnabled = !loading

        // Disable input fields during loading
        firstNameEditText.isEnabled = !loading
        lastNameEditText.isEnabled = !loading
        emailEditText.isEnabled = !loading
        passwordEditText.isEnabled = !loading
    }

    override fun onBackPressed() {
        if (!isRegistering) {
            super.onBackPressed()
        }
    }
}