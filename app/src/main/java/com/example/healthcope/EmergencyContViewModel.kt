package com.example.healthcope

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthcope.model.EmergencyContact

data class EmergencyContViewModel(private val repository: EmergencyContactRepo): ViewModel(){
    val allContacts: LiveData<List<EmergencyContact>> = repository.allContact

    fun addContact(contact: EmergencyContact) {
        repository.addContact(contact)
    }

    suspend fun updateContact(contact: EmergencyContact) {
        repository.updateContact(contact)
    }
}

class ContactViewModelFactory(private val repository: EmergencyContactRepo)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmergencyContViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmergencyContViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
