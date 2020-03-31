package com.dohun.news.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.dohun.news.R
import com.dohun.news.databinding.ActivityMainBinding
import com.dohun.news.ui.adapter.NewsListAdapter
import com.dohun.news.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    private var isBackPressed = false

    override fun onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed()
            return
        }
        isBackPressed = true
        Snackbar.make(binding.clRoot, R.string.message_back_press_to_finish, Snackbar.LENGTH_SHORT).show()
        lifecycleScope.launch {
            delay(2000)
            isBackPressed = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupSkeleton()
        setupSnackbar()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    @SuppressLint("InflateParams")
    private fun setupSkeleton() {
        val deviceHeight = resources.displayMetrics.heightPixels
        val skeletonHeight = resources.getDimensionPixelSize(R.dimen.news_item_height)

        repeat(deviceHeight / skeletonHeight) {
            binding.llSkeleton.addView(layoutInflater.inflate(R.layout.layout_news_skeleton, null))
        }
    }

    private fun setupSnackbar() {
        viewModel.retrySnackbarEvent.observe(this, Observer {
            Snackbar.make(binding.clRoot, R.string.message_network_insecure, Snackbar.LENGTH_INDEFINITE).apply {
                setAction(R.string.retry) {
                    viewModel.refreshNewsList()
                    dismiss()
                }
                show()
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvNews.run {
            setHasFixedSize(true)
            adapter = NewsListAdapter()
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.srlNews.setOnRefreshListener { viewModel.refreshNewsList() }
    }
}
