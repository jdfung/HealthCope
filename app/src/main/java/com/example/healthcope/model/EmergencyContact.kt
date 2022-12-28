package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class EmergencyContact( @SerializedName("id")
                             var id: Int = 0,

                             @SerializedName("contact")
                             var contact: String = ""
)
