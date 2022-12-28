package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class PhysicalConditionRecordEntry (
    @SerializedName("healthLogID")
    var healthLogID: Int = 0,

    @SerializedName("rating")
    var rating: Int = 0,

    @SerializedName("note")
    var note: String = ""
)