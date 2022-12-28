package com.example.healthcope.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_NEWS = "https://newsapi.org/V2/"

class RetrofitClientNews {

    companion object {
        private var INSTANCE: RetrofitClientNews? = null
        private var api: RetrofitAPINews? = null

        @Synchronized
        fun getInstance(): RetrofitClientNews? {
            if (INSTANCE == null) {
                INSTANCE = RetrofitClientNews()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_NEWS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(RetrofitAPINews::class.java)
            }
            return INSTANCE
        }
    }

    fun getApi(): RetrofitAPINews? {
        return api
    }
}