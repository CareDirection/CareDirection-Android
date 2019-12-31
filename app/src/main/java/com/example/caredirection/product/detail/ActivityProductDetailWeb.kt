package com.example.caredirection.product.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.caredirection.R

class ActivityProductDetailWeb : AppCompatActivity() {

    private lateinit var webView : WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_web)

        webView = findViewById(R.id.activity_product_detail_webview)
        var mWebSettings = webView.settings
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebSettings.setSupportZoom(true) // 화면 줌 허용 여부
        webView.loadUrl("https://www.google.co.in/")
    }

}
