package com.example.healthcope.application

import android.app.Application
import com.example.healthcope.repository.NewsRepository
import com.example.healthcope.repository.PersonalHealthLogRepository

class SingletonApplication: Application() {

    val repositoryPersonalHealthLog by lazy { PersonalHealthLogRepository() }
    val repositoryNews by lazy { NewsRepository() }

}