package com.example.newsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import androidx.constraintlayout.widget.VirtualLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adaper: Adaper
    private val artical = mutableListOf<Artical>()
    var pageNum = 1
    var totalResults = -1
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()

        MobileAds.initialize(this)


        adaper = Adaper(this@MainActivity, artical)
        newsList.adapter = adaper
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        newsList.layoutManager = layoutManager
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                newsList.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {
                    pageNum++
                    getNews()
                }
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this@MainActivity)
                }
            }
        })
    }

    private fun getNews() {
        val new = NewsSer.newsInstenc.getHeadlines("in", pageNum)
        new.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    totalResults = news.totalResults
                    artical.addAll(news.articles)
                    adaper.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("NewsApp", "Error in Fetching ")
            }
        })
    }
}