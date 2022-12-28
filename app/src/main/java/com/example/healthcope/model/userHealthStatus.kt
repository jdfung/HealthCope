package com.example.healthcope.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
@Parcelize
data class userHealthStatus(@SerializedName("id")
                            var id: Int = 0,

                            @SerializedName("date")
                            var date: Date = Date(),

                            @SerializedName("heartRate")
                            var heartRate: Int = 0,

                            @SerializedName("bloodPressure")
                            var bloodPressure: Int = 0,

                            @SerializedName("bodyTemp")
                            var bodyTemp: Int = 0,

                            @SerializedName("bloodGlucose")
                            var bloodGlucose: Int = 0,

                            @SerializedName("oxygenSaturation")
                            var oxygenSaturation: Int = 0,

                            @SerializedName("height")
                            var height: Int = 0,

                            @SerializedName("weight")
                            var weight: Int = 0,

                            @SerializedName("bodyFat")
                            var bodyFat: Int = 0,
): Parcelable
