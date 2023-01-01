package com.example.healthcope.application

import android.app.Application
import com.example.healthcope.featureHealthAlerts.EmergencyContactRepo
import com.example.healthcope.featureHealthAlerts.RemindersRepo
import com.example.healthcope.repository.NewsRepository
import com.example.healthcope.repository.PersonalHealthLogRepository

class HealthCopeApplication: Application() {

    val repository by lazy { EmergencyContactRepo() }

    val repository1 by lazy { RemindersRepo() }

    val repositoryPersonalHealthLog by lazy { PersonalHealthLogRepository() }
    val repositoryNews by lazy { NewsRepository() }
}