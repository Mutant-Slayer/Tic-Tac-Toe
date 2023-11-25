package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val iNext = Intent(this, PlayerDetailsActivity::class.java)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(iNext)
            finish()
        }, 2000)

    }
}
