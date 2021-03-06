package com.dohun.news.ui.news

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.dohun.news.model.NewsModel
import com.dohun.news.R
import com.dohun.news.databinding.ActivityNewsBinding
import com.dohun.news.ui.base.BaseActivity
import com.google.android.material.appbar.AppBarLayout

class NewsActivity : BaseActivity<ActivityNewsBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_news

    private val newsModel: NewsModel by lazy { intent.getParcelableExtra("news") as NewsModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.news = newsModel

        setupWebView()
        setupToolbar()
    }

    private fun setupWebView() {
        binding.wbNews.run {
            webViewClient = object: WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    binding.pbNews.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.pbNews.visibility = View.GONE
                }
            }

            if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                && (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
            ) {
                settings.forceDark = WebSettings.FORCE_DARK_ON
            }

            settings.displayZoomControls = false
            settings.loadWithOverviewMode = true
            settings.builtInZoomControls = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
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
