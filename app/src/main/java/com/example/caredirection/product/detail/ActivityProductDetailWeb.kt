package com.example.caredirection.product.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.caredirection.R

class ActivityProductDetailWeb : AppCompatActivity() {

    private lateinit var webView : WebView
    private lateinit var sendedSiteLink: String
    //var link: String = intent.getStringExtra("link")!!.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_web)


        sendedSiteLink = intent.getStringExtra("link")!!.toString()

        webView = findViewById(R.id.activity_product_detail_webview)
        var mWebSettings = webView.settings
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        webView.settings.apply { javaScriptEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
            domStorageEnabled = true // 로컬저장소 허용 여부
        }
        //mWebSettings.setSupportZoom(true) // 화면 줌 허용 여부
        webView.loadUrl(sendedSiteLink)

    }

}
