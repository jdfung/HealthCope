package com.example.healthcope.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.model.NewsResponse
import com.example.healthcope.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    init {
        getNews("chronic illness", API_KEY)
    }

    private val _news: MutableLiveData<NewsResponse> = MutableLiveData()

    fun readNews() : LiveData<NewsResponse> {
        return _news
    }

    fun getNews(q: String?, apiKey: String?): MutableLiveData<NewsResponse> {
        var api: RetrofitAPINews? = null
        api = RetrofitClientNews.getInstance()?.getApi()
        val call: Call<NewsResponse?> = api?.getNewsList(q, apiKey)!!

        call.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(call: Call<NewsResponse?>, response: Response<NewsResponse?>) {
                if (response.isSuccessful) {
                   _news.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }
        })
        return _news
    }
}