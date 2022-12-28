package com.example.healthcope.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_PERSONAL_HEALTH_LOG = "https://63a8374f100b7737b979de8a.mockapi.io/"

class RetrofitClientPersonalHealthLog {
    companion object {
        private var INSTANCE: RetrofitClientPersonalHealthLog? = null
        private var api: RetrofitAPIPersonalHealthLog? = null

        @Synchronized
        fun getInstance(): RetrofitClientPersonalHealthLog? {
            if(INSTANCE == null) {
                INSTANCE = RetrofitClientPersonalHealthLog()
                var retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_PERSONAL_HEALTH_LOG)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                api = retrofit.create(RetrofitAPIPersonalHealthLog::class.java)
            }

            return INSTANCE
        }

    }

    fun getApi(): RetrofitAPIPersonalHealthLog? {
        return api
    }
}