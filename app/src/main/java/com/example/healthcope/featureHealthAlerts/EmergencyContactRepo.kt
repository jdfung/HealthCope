package com.example.healthcope.featureHealthAlerts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.model.EmergencyContact
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmergencyContactRepo() {
    val allContact: LiveData<List<EmergencyContact>>
        get() = _contact

    private val _contact: MutableLiveData<List<EmergencyContact>> = MutableLiveData()

    init {
        getAllContact()
    }

    fun getAllContact(): MutableLiveData<List<EmergencyContact>> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<EmergencyContact>> = api?.getAllContacts()!!
        call.enqueue(object: Callback<List<EmergencyContact>> {
            override fun onResponse(call: Call<List<EmergencyContact>>, response: Response<List<EmergencyContact>>) {
                if(response.isSuccessful) {
                    _contact.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<EmergencyContact>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _contact
    }


    fun addContact(contact: EmergencyContact): LiveData<EmergencyContact> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val data: MutableLiveData<EmergencyContact> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<EmergencyContact> = api?.addContact(contact)!!
            if(response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.value = response.body()
                    getAllContact()
                }
            }
        }
        return data
    }

    fun updateContact(contact: EmergencyContact) {
//        noteDao.updateContact(contact)
    }
}