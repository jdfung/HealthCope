package com.example.healthcope.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.model.*
import com.example.healthcope.network.RetrofitAPIPersonalHealthLog
import com.example.healthcope.network.RetrofitClientPersonalHealthLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalHealthLogRepository {

    init {
        getPersonalHealthLog()
    }

    private val _personalHealthLog: MutableLiveData<List<HealthLog>> = MutableLiveData()
    private val _healthLogByID : MutableLiveData<HealthLog> = MutableLiveData()
    private val _mentalConditionRecordEntryByID : MutableLiveData<MentalConditionRecordEntry> = MutableLiveData()
    private val _physicalConditionRecordEntryByID : MutableLiveData<PhysicalConditionRecordEntry> = MutableLiveData()
    private val _drugUsageRecordByID : MutableLiveData<DrugUsageRecord> = MutableLiveData()
//    private val _drugUsageRecordEntriesByID : MutableLiveData<MutableList<DrugUsageRecordEntry>> = MutableLiveData()

    fun readPersonalHealthLog() : LiveData<List<HealthLog>>{
        return _personalHealthLog
    }

    fun readHealthLogByID() : LiveData<HealthLog>{
        return _healthLogByID
    }

    fun readMentalConditionRecordEntryByID() : LiveData<MentalConditionRecordEntry>{
        return _mentalConditionRecordEntryByID
    }

    fun readPhysicalConditionRecordEntryByID() : LiveData<PhysicalConditionRecordEntry>{
        return _physicalConditionRecordEntryByID
    }

    fun readDrugUsageRecordByID() : LiveData<DrugUsageRecord>{
        return _drugUsageRecordByID
    }

//    fun readDrugUsageRecordEntriesByID() : LiveData<MutableList<DrugUsageRecordEntry>>{
//        return _drugUsageRecordEntriesByID
//    }

    //Personal health log

    private fun getPersonalHealthLog(): MutableLiveData<List<HealthLog>> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()
        val call: Call<List<HealthLog>> = api?.getPersonalHealthLog()!!

        call.enqueue(object: Callback<List<HealthLog>> {
            override fun onResponse(call: Call<List<HealthLog>>, response: Response<List<HealthLog>>) {
                if(response.isSuccessful) {
                    _personalHealthLog.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<HealthLog>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _personalHealthLog
    }

    fun getHealthLogByID(id : Int): MutableLiveData<HealthLog>{
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<HealthLog> = api?.getHealthLogByID(id)!!
        call.enqueue(object: Callback<HealthLog> {
            override fun onResponse(call: Call<HealthLog>, response: Response<HealthLog>) {
                if(response.isSuccessful) {
                    _healthLogByID.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<HealthLog>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _healthLogByID
    }

    fun postHealthLog(log: HealthLog): LiveData<HealthLog> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<HealthLog> = api?.postHealthLog(log)!!
        call.enqueue(object: Callback<HealthLog> {
            override fun onResponse(call: Call<HealthLog>, response: Response<HealthLog>) {
                if(response.isSuccessful) {
                    _healthLogByID.postValue(response.body())
                    getPersonalHealthLog()
                }
            }

            override fun onFailure(call: Call<HealthLog>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _healthLogByID
    }

    //Mental condition

    fun getMentalConditionRecordEntryByID(id : Int): MutableLiveData<MentalConditionRecordEntry>{
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<MentalConditionRecordEntry> = api?.getMentalConditionRecordEntryByID(id)!!
        call.enqueue(object: Callback<MentalConditionRecordEntry> {
            override fun onResponse(call: Call<MentalConditionRecordEntry>, response: Response<MentalConditionRecordEntry>) {
                if(response.isSuccessful) {
                    _mentalConditionRecordEntryByID.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MentalConditionRecordEntry>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _mentalConditionRecordEntryByID
    }

    fun postMentalConditionRecordEntry(entry: MentalConditionRecordEntry): LiveData<MentalConditionRecordEntry> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<MentalConditionRecordEntry> = api?.postMentalConditionRecordEntry(entry)!!
        call.enqueue(object: Callback<MentalConditionRecordEntry> {
            override fun onResponse(call: Call<MentalConditionRecordEntry>, response: Response<MentalConditionRecordEntry>) {
                if(response.isSuccessful) {
                    _mentalConditionRecordEntryByID.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MentalConditionRecordEntry>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _mentalConditionRecordEntryByID
    }

    fun putMentalConditionRecordByID(healthID: Int, newEntry: MentalConditionRecordEntry): LiveData<MentalConditionRecordEntry> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<MentalConditionRecordEntry> = api?.putMentalConditionRecordEntryByID(healthID, newEntry)!!
        call.enqueue(object: Callback<MentalConditionRecordEntry> {
            override fun onResponse(call: Call<MentalConditionRecordEntry>, response: Response<MentalConditionRecordEntry>) {
                if(response.isSuccessful) {
                    _mentalConditionRecordEntryByID.postValue(response.body())
                    //_drugUsageRecordEntriesByID.postValue(response.body()!!.drugUsageRecord)
                    //getDrugUsageRecordByID(healthID)
                }
            }

            override fun onFailure(call: Call<MentalConditionRecordEntry>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }
        })

        return _mentalConditionRecordEntryByID
    }

    //Physical condition

    fun getPhysicalConditionRecordEntryByID(id : Int): MutableLiveData<PhysicalConditionRecordEntry>{
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<PhysicalConditionRecordEntry> = api?.getPhysicalConditionRecordEntryByID(id)!!
        call.enqueue(object: Callback<PhysicalConditionRecordEntry> {
            override fun onResponse(call: Call<PhysicalConditionRecordEntry>, response: Response<PhysicalConditionRecordEntry>) {
                if(response.isSuccessful) {
                    _physicalConditionRecordEntryByID.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PhysicalConditionRecordEntry>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _physicalConditionRecordEntryByID
    }

    fun postPhysicalConditionRecordEntry(entry: PhysicalConditionRecordEntry): LiveData<PhysicalConditionRecordEntry> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<PhysicalConditionRecordEntry> = api?.postPhysicalConditionRecordEntry(entry)!!
        call.enqueue(object: Callback<PhysicalConditionRecordEntry> {
            override fun onResponse(call: Call<PhysicalConditionRecordEntry>, response: Response<PhysicalConditionRecordEntry>) {
                if(response.isSuccessful) {
                    _physicalConditionRecordEntryByID.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<PhysicalConditionRecordEntry>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _physicalConditionRecordEntryByID
    }

    fun putPhysicalConditionRecordByID(healthID: Int, newEntry: PhysicalConditionRecordEntry): LiveData<PhysicalConditionRecordEntry> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<PhysicalConditionRecordEntry> = api?.putPhysicalConditionRecordEntryByID(healthID, newEntry)!!
        call.enqueue(object: Callback<PhysicalConditionRecordEntry> {
            override fun onResponse(call: Call<PhysicalConditionRecordEntry>, response: Response<PhysicalConditionRecordEntry>) {
                if(response.isSuccessful) {
                    _physicalConditionRecordEntryByID.postValue(response.body())
                    //_drugUsageRecordEntriesByID.postValue(response.body()!!.drugUsageRecord)
                    //getDrugUsageRecordByID(healthID)
                }
            }

            override fun onFailure(call: Call<PhysicalConditionRecordEntry>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }
        })

        return _physicalConditionRecordEntryByID
    }

    //Drug usage

    fun getDrugUsageRecordByID(id : Int): MutableLiveData<DrugUsageRecord>{
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<DrugUsageRecord> = api?.getDrugUsageRecordByID(id)!!
        call.enqueue(object: Callback<DrugUsageRecord> {
            override fun onResponse(call: Call<DrugUsageRecord>, response: Response<DrugUsageRecord>) {
                if(response.isSuccessful) {
                    _drugUsageRecordByID.postValue(response.body())
                    //_drugUsageRecordEntriesByID.postValue(response.body()!!.drugUsageRecord)
                }
            }

            override fun onFailure(call: Call<DrugUsageRecord>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _drugUsageRecordByID
    }

    fun postDrugUsageRecord(record: DrugUsageRecord): LiveData<DrugUsageRecord> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<DrugUsageRecord> = api?.postDrugUsageRecord(record)!!
        call.enqueue(object: Callback<DrugUsageRecord> {
            override fun onResponse(call: Call<DrugUsageRecord>, response: Response<DrugUsageRecord>) {
                if(response.isSuccessful) {
                    _drugUsageRecordByID.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DrugUsageRecord>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _drugUsageRecordByID
    }

    fun putDrugUsageRecordByID(healthID: Int, newRecord: DrugUsageRecord): LiveData<DrugUsageRecord> {
        var api: RetrofitAPIPersonalHealthLog? = null
        api = RetrofitClientPersonalHealthLog.getInstance()?.getApi()

        val call: Call<DrugUsageRecord> = api?.putDrugUsageRecordByID(healthID, newRecord)!!
        call.enqueue(object: Callback<DrugUsageRecord> {
            override fun onResponse(call: Call<DrugUsageRecord>, response: Response<DrugUsageRecord>) {
                if(response.isSuccessful) {
                    _drugUsageRecordByID.postValue(response.body())
                    //_drugUsageRecordEntriesByID.postValue(response.body()!!.drugUsageRecord)
                    //getDrugUsageRecordByID(healthID)
                }
            }

            override fun onFailure(call: Call<DrugUsageRecord>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }
        })

        return _drugUsageRecordByID
    }
}