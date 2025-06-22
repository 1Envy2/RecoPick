package com.example.utstam

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        Handler().postDelayed({
            startActivity(
                Intent(
                    this@SplashScreenActivity,
                    OnboardingActivity::class.java  // Changed from MainActivity to OnboardingActivity
                )
            )
            finish()
        }, 2000)
    }
}