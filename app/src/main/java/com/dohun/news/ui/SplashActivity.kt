package com.dohun.news.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dohun.news.R
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_WAIT_MILLI = 1300L
    }

    init {
        lifecycleScope.launchWhenCreated {
            delay(SPLASH_WAIT_MILLI)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
