package com.example.tokobunda.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tokobunda.Login.Login
import com.example.tokobunda.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME : Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, SPLASH_TIME

        )
    }
}