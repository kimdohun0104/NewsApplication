package com.dohun.news.ui

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.dohun.model.NewsModel
import com.dohun.news.R
import com.dohun.news.databinding.ActivityNewsBinding
import com.google.android.material.appbar.AppBarLayout

class NewsActivity : AppCompatActivity() {

    private val newsModel: NewsModel by lazy { intent.getParcelableExtra("news") as NewsModel }

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        binding.news = newsModel

        setupWebView()
        setupToolbar()
    }

    private fun setupWebView() {
        binding.wbNews.webViewClient = WebViewClient()
    }

    private fun setupToolbar() {
        binding.run {
            tbNews.setNavigationOnClickListener { finish() }

            abNews.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (ctNews.height + verticalOffset < ctNews.scrimVisibleHeightTrigger) {
                    setNavigationIconTint(R.color.colorBlack)
                    animateTitle(1f)
                } else {
                    setNavigationIconTint(R.color.colorWhite)
                    animateTitle(0f)
                }
            })
        }
    }

    private fun setNavigationIconTint(color: Int) =
        binding.tbNews.navigationIcon?.setTint(ContextCompat.getColor(this, color))

    private fun animateTitle(alpha: Float) =
        binding.tvTitle.animate().alpha(alpha).setDuration(50L)
}
