package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class DrugUsageRecordEntry(
    @SerializedName("drugName")
    var drugName : String = "",

    @SerializedName("rating")
    var rating : Int = 0,

    @SerializedName("note")
    var note : String = ""
)
