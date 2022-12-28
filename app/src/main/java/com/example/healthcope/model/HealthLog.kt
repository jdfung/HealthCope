package com.example.healthcope.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class HealthLog (

    @SerializedName("healthLogID")
    var healthLogID: Int = 0,

    @SerializedName("date")
    var date: String = ""

)