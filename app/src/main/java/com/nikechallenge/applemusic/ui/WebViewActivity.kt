package com.nikechallenge.applemusic.ui

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.nikechallenge.applemusic.LocalCache
import com.nikechallenge.applemusic.R
import com.nikechallenge.applemusic.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAndLoadWebView(binding.webView)
    }

    private fun initAndLoadWebView(webView: WebView) {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowContentAccess = false
        webSettings.allowFileAccess = false
        webView.webChromeClient = WebChromeClient()
        LocalCache.songDetail?.let { it.artistDetail?.let { it1 -> webView.loadUrl(it1) } }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return true
            }
        }
    }
}