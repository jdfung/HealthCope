package com.example.healthcope

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthcope.model.EmergencyContact
import com.example.healthcope.model.Reminders

data class ReminderViewModel(private val repository1: RemindersRepo): ViewModel(){
    val allReminders: LiveData<List<Reminders>> = repository1.allReminders

    fun addReminder(reminder: Reminders) {
        repository1.addReminder(reminder)
    }

    suspend fun updateReminder(reminder: Reminders) {
        repository1.updateReminder(reminder)
    }
}

class ReminderViewModelFactory(private val repository1: RemindersRepo)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(repository1) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
