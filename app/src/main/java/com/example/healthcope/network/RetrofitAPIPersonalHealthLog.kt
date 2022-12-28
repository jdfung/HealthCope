package com.example.healthcope.network

import com.example.healthcope.model.DrugUsageRecord
import com.example.healthcope.model.HealthLog
import com.example.healthcope.model.MentalConditionRecordEntry
import com.example.healthcope.model.PhysicalConditionRecordEntry
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitAPIPersonalHealthLog {

    //Health Log

    @GET("/personalHealthLog")
    fun getPersonalHealthLog(): Call<List<HealthLog>>
//  fun getAllNotes(): Response<List<Note>>

    @GET("/personalHealthLog/{id}")
    fun getHealthLogByID(@Path("id") id: Int): Call<HealthLog>

    @POST("/personalHealthLog")
    fun postHealthLog(@Body note: HealthLog): Call<HealthLog>

    //Mental Condition

    @GET("/mentalCondition/{id}")
    fun getMentalConditionRecordEntryByID(@Path("id") id: Int): Call<MentalConditionRecordEntry>

    @POST("/mentalCondition")
    fun postMentalConditionRecordEntry(@Body note: MentalConditionRecordEntry): Call<MentalConditionRecordEntry>

    @PUT("/mentalCondition/{id}")
    fun putMentalConditionRecordEntryByID(@Path("id") id: Int, @Body entry: MentalConditionRecordEntry): Call<MentalConditionRecordEntry>

    //Physical Condition

    @GET("/physicalCondition/{id}")
    fun getPhysicalConditionRecordEntryByID(@Path("id") id: Int): Call<PhysicalConditionRecordEntry>

    @POST("/physicalCondition")
    fun postPhysicalConditionRecordEntry(@Body note: PhysicalConditionRecordEntry): Call<PhysicalConditionRecordEntry>

    @PUT("/physicalCondition/{id}")
    fun putPhysicalConditionRecordEntryByID(@Path("id") id: Int, @Body entry: PhysicalConditionRecordEntry): Call<PhysicalConditionRecordEntry>

    //Drug Usage

    @GET("/drugUsage/{id}")
    fun getDrugUsageRecordByID(@Path("id") id: Int): Call<DrugUsageRecord>

    @POST("/drugUsage")
    fun postDrugUsageRecord(@Body note: DrugUsageRecord): Call<DrugUsageRecord>

    @PUT("/drugUsage/{id}")
    fun putDrugUsageRecordByID(@Path("id") id: Int, @Body record: DrugUsageRecord): Call<DrugUsageRecord>

}