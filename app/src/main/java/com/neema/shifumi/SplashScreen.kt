package com.neema.shifumi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    companion object {
        private val TAG = SplashScreen::class.java.simpleName
        const val SPLASH_TIME_OUT: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "------------------ $TAG OnCreate ------------------ ")
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
