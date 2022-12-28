package com.example.healthcope.network

import com.example.healthcope.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "a1ecab3d283d446593313148af691881"

interface RetrofitAPINews {
    @GET("everything")
    fun getNewsList(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?
    ): Call<NewsResponse?>?

}
