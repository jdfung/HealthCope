package com.example.healthcope.model

import com.google.gson.annotations.SerializedName

data class DrugUsageRecord(
    @SerializedName("healthLogID")
    var healthLogID: Int = 0,

    @SerializedName("drugUsageRecord")
    var drugUsageRecord: MutableList<DrugUsageRecordEntry> = mutableListOf<DrugUsageRecordEntry>()
)
