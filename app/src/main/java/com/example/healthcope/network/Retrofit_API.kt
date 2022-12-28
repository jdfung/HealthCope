package com.example.healthcope.network

import com.example.healthcope.model.EmergencyContact
import com.example.healthcope.model.Reminders
import com.example.healthcope.model.userAccount
import com.example.healthcope.model.userHealthStatus
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Retrofit_API {
    @GET("/emergencyContacts")
    fun getAllContacts(): Call<List<EmergencyContact>>

    @POST("/emergencyContacts")
    suspend fun addContact(@Body contact: EmergencyContact): Response<EmergencyContact>

    @GET("/HealthStatus")
    fun getAllStatus(): Call<List<userHealthStatus>>

    @GET("/Reminders")
    fun getAllReminders(): Call<List<Reminders>>

    @POST("/Reminders")
    suspend fun addReminder(@Body reminder: Reminders): Response<Reminders>

    @GET("/userAccount")
    fun getAllAccounts(): Call<List<userAccount>>

    @POST("/userAccount")
    suspend fun register(@Body account: userAccount): Response<userAccount>

}