package com.example.healthcope

import androidx.lifecycle.*
import com.example.healthcope.model.*
import com.example.healthcope.model.HealthLog
import com.example.healthcope.repository.PersonalHealthLogRepository

class PersonalHealthLogViewModel(private val repository: PersonalHealthLogRepository): ViewModel() {

    val personalHealthLog: LiveData<List<HealthLog>> = repository.readPersonalHealthLog()
    val healthLogByID : LiveData<HealthLog> = repository.readHealthLogByID()
    val mentalConditionRecordEntryByID : LiveData<MentalConditionRecordEntry> = repository.readMentalConditionRecordEntryByID()
    val physicalConditionRecordEntryByID: LiveData<PhysicalConditionRecordEntry> = repository.readPhysicalConditionRecordEntryByID()
    val drugUsageRecordByID : LiveData<DrugUsageRecord> = repository.readDrugUsageRecordByID()
//    val drugUsageRecordEntries : LiveData<MutableList<DrugUsageRecordEntry>> = repository.readDrugUsageRecordEntriesByID()

//    fun getAllNotes(): Flow<List<Note>> {
//        return repository.allNotes
//    }

    //Health log

    fun loadHealthLogByID(id: Int){
        repository.getHealthLogByID(id)
    }

    fun postHealthLog(healthLog: HealthLog) {
        repository.postHealthLog(healthLog)
    }

    //Mental condition

    fun loadMentalConditionRecordEntryByID(id : Int){
        repository.getMentalConditionRecordEntryByID(id)
    }

    fun postMentalConditionRecordEntry(entry: MentalConditionRecordEntry) {
        repository.postMentalConditionRecordEntry(entry)
    }

    fun putMentalConditionRecordEntryByID(id: Int, entry: MentalConditionRecordEntry){
        repository.putMentalConditionRecordByID(id, entry)
    }

    //Physical condition

    fun loadPhysicalConditionRecordEntryByID(id : Int){
        repository.getPhysicalConditionRecordEntryByID(id)
    }

    fun postPhysicalConditionRecordEntry(entry: PhysicalConditionRecordEntry) {
        repository.postPhysicalConditionRecordEntry(entry)
    }

    fun putPhysicalConditionRecordEntryByID(id : Int, record: PhysicalConditionRecordEntry){
        repository.putPhysicalConditionRecordByID(id, record)
    }

    //Drug usage

    fun loadDrugUsageRecordByID(id : Int){
        repository.getDrugUsageRecordByID(id)
    }

    fun postDrugUsageRecord(record: DrugUsageRecord) {
        repository.postDrugUsageRecord(record)
    }

    fun putDrugUsageRecordByID(id : Int, record: DrugUsageRecord){
        repository.putDrugUsageRecordByID(id, record)
    }
}

class PersonalHealthLogViewModelFactory(private val repository: PersonalHealthLogRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonalHealthLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonalHealthLogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}