package com.dohun.news.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dohun.news.BuildConfig
import com.dohun.news.R
import com.dohun.news.databinding.ActivitySplashBinding
import com.dohun.news.ui.base.BaseActivity
import kotlinx.coroutines.delay

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_splash

    init {
        lifecycleScope.launchWhenCreated {
            delay(SPLASH_WAIT_MILLI)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvVersion.text = "v${BuildConfig.VERSION_NAME}"
    }

    companion object {
        private const val SPLASH_WAIT_MILLI = 1300L
    }
}
