package com.example.healthcope

import android.app.Application

class HealthCopeApplication: Application() {

    val repository by lazy { EmergencyContactRepo() }

    val repository1 by lazy { RemindersRepo() }
}