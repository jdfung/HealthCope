package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class NewsSource(
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null
)
