package com.example.healthcope.model

import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.sql.Time

data class Reminders(@SerializedName("id")
                     var id: Int = 0,

                    @SerializedName("drugs")
                    var drugs: String = ""

)
