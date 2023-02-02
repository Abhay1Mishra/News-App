package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail_veiw.*

class DetailVeiw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_veiw)
        val url =intent.getStringExtra("Url")
        if (url!=null){
            webView.settings.javaScriptEnabled=true
            webView.webViewClient =object :WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility=View.GONE
                    webView.visibility=View.VISIBLE
                }
            }
            webView.loadUrl(url)
        }
    }
}