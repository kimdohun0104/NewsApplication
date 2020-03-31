package com.dohun.news.ui

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.dohun.model.NewsModel
import com.dohun.news.R
import com.dohun.news.databinding.ActivityNewsBinding
import com.google.android.material.appbar.AppBarLayout

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private val newsModel: NewsModel by lazy { intent.getParcelableExtra("news") as NewsModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        binding.news = newsModel

        setupWebView()
        setupToolbar()
    }

    private fun setupWebView() {
        binding.wbNews.run {
            webViewClient = WebViewClient()
            settings.displayZoomControls = false
            settings.loadWithOverviewMode = true
            settings.builtInZoomControls = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)

            if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                && (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
            ) {
                settings.forceDark = WebSettings.FORCE_DARK_ON
            }
        }
    }

    private fun setupToolbar() {
        binding.run {
            tbNews.setNavigationOnClickListener { finish() }

            abNews.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (ctNews.height + verticalOffset < ctNews.scrimVisibleHeightTrigger) {
                    setNavigationIconTint(ContextCompat.getColor(this@NewsActivity, R.color.colorBlack))
                    animateTitle(1f)
                } else {
                    setNavigationIconTint(Color.WHITE)
                    animateTitle(0f)
                }
            })
        }
    }

    private fun setNavigationIconTint(color: Int) =
        binding.tbNews.navigationIcon?.setTint(color)

    private fun animateTitle(alpha: Float) =
        binding.tvToolbarTitle.animate().alpha(alpha).setDuration(50L)
}
