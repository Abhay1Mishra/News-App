package com.example.newsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val Base_Url ="https://newsapi.org/"
const val Api_Key =  "5000085dbbcd40b79f71cb77dd72c48a"
interface NewsServises {
    @GET("v2/top-headlines?apiKey=$Api_Key")
    fun getHeadlines (@Query("country") country:String,@Query("page") page:Int):Call<News>


}
object NewsSer{
    val newsInstenc:NewsServises
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstenc =retrofit.create(NewsServises::class.java)
    }
}