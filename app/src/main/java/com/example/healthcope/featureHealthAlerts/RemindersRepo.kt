package com.example.healthcope.featureHealthAlerts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.model.Reminders
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemindersRepo {
    val allReminders: LiveData<List<Reminders>>
        get() = _reminder

    private val _reminder: MutableLiveData<List<Reminders>> = MutableLiveData()

    init {
        getAllReminder()
    }

    fun getAllReminder(): MutableLiveData<List<Reminders>> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<Reminders>> = api?.getAllReminders()!!
        call.enqueue(object: Callback<List<Reminders>> {
            override fun onResponse(call: Call<List<Reminders>>, response: Response<List<Reminders>>) {
                if(response.isSuccessful) {
                    _reminder.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Reminders>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _reminder
    }


    fun addReminder(reminder: Reminders): LiveData<Reminders> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val data: MutableLiveData<Reminders> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<Reminders> = api?.addReminder(reminder)!!
            if(response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.value = response.body()
                    getAllReminder()
                }
            }
        }
        return data
    }

    fun updateReminder(reminder: Reminders) {
//        noteDao.updateContact(contact)
    }

}