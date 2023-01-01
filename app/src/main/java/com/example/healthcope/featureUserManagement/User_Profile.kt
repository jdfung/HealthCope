package com.example.healthcope.featureUserManagement

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.R
import com.example.healthcope.featureAppSettings.Settings
import com.example.healthcope.featureHealthAlerts.DialogWithData
import com.example.healthcope.featureHealthAlerts.add_emergency_contacts
import com.example.healthcope.featureHealthAlerts.reminders_page
import com.example.healthcope.featureHealthInformation.LearnActivity
import com.example.healthcope.featureHealthVisualisation.ongoing_health_report_page
import com.example.healthcope.featureTrackAndCheckIn.ManagePersonalHealthActivity
import com.example.healthcope.featureUserHealthProfile.MainActivity
import com.example.healthcope.model.EmergencyContact
import com.example.healthcope.model.Reminders
import com.example.healthcope.model.userHealthStatus
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class User_Profile : AppCompatActivity() {
    lateinit var edit_profile_button: Button
    lateinit var health_report_button: Button
    lateinit var emergency_contact_button: Button
    lateinit var reminders_button: Button
    lateinit var to_home_activity: TextView
    lateinit var to_manage_activity: TextView
    lateinit var to_learn_activity: TextView

    lateinit var avgHeartPulse: TextView
    lateinit var avgBloodP: TextView
    lateinit var avgBloodO: TextView
    lateinit var avgWeightAll: TextView
    lateinit var avgTempAll: TextView
    lateinit var avgFatAll: TextView
    lateinit var activeEContact: TextView
    lateinit var activeReminder: TextView

    val allHeartRateAvg: ArrayList<Int> = arrayListOf()
    val allBloodPAvg: ArrayList<Int> = arrayListOf()
    val allBloodOAvg: ArrayList<Int> = arrayListOf()
    val allWeightAvg: ArrayList<Int> = arrayListOf()
    val allTempAvg: ArrayList<Int> = arrayListOf()
    val allFatAvg: ArrayList<Int> = arrayListOf()

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        edit_profile_button = findViewById<Button>(R.id.btn_edit_profile)
        health_report_button = findViewById<Button>(R.id.open_health_report_btn)
        emergency_contact_button = findViewById<Button>(R.id.open_emergency_contact_btn)
        reminders_button = findViewById<Button>(R.id.open_reminders_btn)
        to_home_activity = findViewById<TextView>(R.id.profileHomeNavOpt)
        to_manage_activity = findViewById<TextView>(R.id.profileManageNavOpt)
        to_learn_activity = findViewById<TextView>(R.id.profileLearnNavOpt)

        avgHeartPulse = findViewById<TextView>(R.id.averageHeartRate)
        avgBloodP = findViewById<TextView>(R.id.averageBloodP)
        avgBloodO = findViewById<TextView>(R.id.averageBloodO)
        avgWeightAll = findViewById<TextView>(R.id.averageWeightAll)
        avgTempAll = findViewById<TextView>(R.id.averageTempAll)
        avgFatAll = findViewById<TextView>(R.id.averageFatAll)
        activeEContact = findViewById<TextView>(R.id.activeContact)
        activeReminder = findViewById<TextView>(R.id.activeReminders)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

        getUserHealthStats(allHeartRateAvg,
            allBloodPAvg,
            allBloodOAvg,
            allWeightAvg,
            allTempAvg,
            allFatAvg)
        getReminderCount()
        getContactCount()

        Log.d(TAG, allHeartRateAvg.toString())

        edit_profile_button.setOnClickListener {
            val Intent = Intent(this, Edit_profile::class.java)
            startActivity(Intent)
        }

        health_report_button.setOnClickListener {
            val Intent = Intent(this, ongoing_health_report_page::class.java)
            startActivity(Intent)
        }

        emergency_contact_button.setOnClickListener {
            val Intent = Intent(this, add_emergency_contacts::class.java)
            startActivity(Intent)
        }

        reminders_button.setOnClickListener {
            val Intent = Intent(this, reminders_page::class.java)
            startActivity(Intent)
        }

        to_home_activity.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

        to_manage_activity.setOnClickListener {
            val Intent = Intent(this, ManagePersonalHealthActivity::class.java)
            startActivity(Intent)
        }

        to_learn_activity.setOnClickListener {
            val Intent = Intent(this, LearnActivity::class.java)
            startActivity(Intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings -> {
                val Intent = Intent(this, Settings::class.java)
                startActivity(Intent)
            }
            R.id.logout -> {
                val Intent = Intent(this, Login::class.java)
                startActivity(Intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val _healthStats: MutableLiveData<List<userHealthStatus>> = MutableLiveData()

    fun getUserHealthStats(
        list1: ArrayList<Int>,
        list2: ArrayList<Int>,
        list3: ArrayList<Int>,
        list4: ArrayList<Int>,
        list5: ArrayList<Int>,
        list6: ArrayList<Int>): MutableLiveData<List<userHealthStatus>> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userHealthStatus>> = api?.getAllStatus()!!
        call.enqueue(object: Callback<List<userHealthStatus>> {
            override fun onResponse(call: Call<List<userHealthStatus>>, response: Response<List<userHealthStatus>>) {
                if(response.isSuccessful) {
                    _healthStats.postValue(response.body())
                    val filterStats: List<userHealthStatus>? = response.body()
                    Log.d(TAG, filterStats.toString())
                    if(filterStats != null) {
                        for ((index, model) in filterStats.withIndex()) {
                            list1.add(model.heartRate)
                            list2.add(model.bloodPressure)
                            list3.add(model.oxygenSaturation)
                            list4.add(model.weight)
                            list5.add(model.bodyTemp)
                            list6.add(model.bodyFat)
                        }

                        avgHeartPulse.text = allHeartRateAvg.average().toInt().toString()
                        avgBloodP.text = allBloodPAvg.average().toInt().toString()
                        avgBloodO.text = allBloodOAvg.average().toInt().toString()
                        avgWeightAll.text = allWeightAvg.average().toInt().toString()
                        avgTempAll.text = allTempAvg.average().toInt().toString()
                        avgFatAll.text = allFatAvg.average().toInt().toString()
                    }

                }
            }

            override fun onFailure(call: Call<List<userHealthStatus>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _healthStats
    }

    fun getContactCount() {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<EmergencyContact>> = api?.getAllContacts()!!
        call.enqueue(object: Callback<List<EmergencyContact>> {
            override fun onResponse(call: Call<List<EmergencyContact>>, response: Response<List<EmergencyContact>>) {
                if(response.isSuccessful) {
                    val allContact = response.body()
                    var contactCount = 0

                    if(allContact != null) {

                        for ((index, model) in allContact.withIndex()) {
                            contactCount+=1
                        }
                        activeEContact.text = ("$contactCount existing contacts")
                        Log.d(DialogWithData.TAG, contactCount.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<EmergencyContact>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }
        })
    }

    fun getReminderCount() {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<Reminders>> = api?.getAllReminders()!!
        call.enqueue(object: Callback<List<Reminders>> {
            override fun onResponse(call: Call<List<Reminders>>, response: Response<List<Reminders>>) {
                if(response.isSuccessful) {
                   val allReminder = response.body()
                    var count = 0

                    if(allReminder != null) {

                        for ((index, model) in allReminder.withIndex()) {
                            count+=1
                        }
                        activeReminder.text = ("$count active reminders")
                        Log.d(DialogWithData.TAG, count.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<Reminders>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })
    }


}