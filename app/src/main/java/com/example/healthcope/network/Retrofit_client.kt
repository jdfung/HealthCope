package com.example.healthcope.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://63a7d7a159fd83b1bb4ad290.mockapi.io/"

class Retrofit_client {
    companion object {
        private var INSTANCE: Retrofit_client? = null
        private var api: Retrofit_API? = null

        @Synchronized
        fun getInstance(): Retrofit_client? {
            if(INSTANCE == null) {
                INSTANCE = Retrofit_client()
                var retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                api = retrofit.create(Retrofit_API::class.java)
            }

            return INSTANCE
        }

    }

    fun getApi(): Retrofit_API? {
        return api
    }
}