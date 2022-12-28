package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class userAccount(@SerializedName("id")
                       var id: Int = 0,

                       @SerializedName("userName")
                       var userName: String = "",

                       @SerializedName("email")
                       var email: String = "",

                       @SerializedName("password")
                       var password: String = "",
)
