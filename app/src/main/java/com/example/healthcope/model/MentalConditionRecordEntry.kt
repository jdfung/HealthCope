package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class MentalConditionRecordEntry (
    @SerializedName("healthLogID")
    var healthLogID: Int = 0,

    @SerializedName("rating")
    var rating: Int = 0,

    @SerializedName("dairy")
    var dairy: String = ""
)