package com.example.healthcope

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.DialogWithData.Companion.TAG
import com.example.healthcope.model.userHealthStatus
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

class ongoing_health_report_page : AppCompatActivity() {
    lateinit var datePicker: LinearLayout
    lateinit var dateRange: TextView
    lateinit var heart_rate_btn: Button
    lateinit var blood_pressure_btn: Button
    lateinit var blood_oxygen_btn: Button
    lateinit var bodily_measurement_btn: Button


    var startDate: Date? = Date()
    var endDate: Date? = Date()
    var heartRateList: ArrayList<Int>? = arrayListOf()
    var bloodPressureList: ArrayList<Int>? = arrayListOf()
    var bodyTempList: ArrayList<Int>? = arrayListOf()
    var bloodGlucoseList: ArrayList<Int>? = arrayListOf()
    var oxygenSaturationList: ArrayList<Int>? = arrayListOf()
    var heightList: ArrayList<Int>? = arrayListOf()
    var weightList: ArrayList<Int>? = arrayListOf()
    var bodyFatList: ArrayList<Int>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ongoing_health_report_page)


        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        datePicker = findViewById<LinearLayout>(R.id.health_report_date)
        dateRange = findViewById<TextView>(R.id.dateRange)
        heart_rate_btn = findViewById<Button>(R.id.to_heart_rate_visuals)
        blood_pressure_btn = findViewById<Button>(R.id.to_blood_pressure_visuals)
        blood_oxygen_btn = findViewById<Button>(R.id.to_blood_oxygen_visuals)
        bodily_measurement_btn = findViewById<Button>(R.id.to_bodily_measure_visuals)

        //click to show data picker
        startDate = Date(System.currentTimeMillis())
        endDate = Date(System.currentTimeMillis())
        dateRange.text = startDate.toString()
        getUserHealthStats()
        datePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                    dateRange.text = datePicker.headerText
                    startDate = Date(datePicker.selection?.first!!)
                    endDate = Date(datePicker.selection?.second!!)
                    getUserHealthStats()
                }
            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
            Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
            Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }

        //click to redirect to heart_rate_visuals
        heart_rate_btn.setOnClickListener {
            val intent = Intent(this, heart_rate_visualization_page::class.java)
            intent.putIntegerArrayListExtra("heartRateData", heartRateList)
            startActivity(intent)
        }

        //click to redirect to blood_pressure_visuals
        blood_pressure_btn.setOnClickListener {
            val intent = Intent(this, blood_pressure_visualisation::class.java)
            intent.putIntegerArrayListExtra("bloodPressureData", bloodPressureList)
            startActivity(intent)
        }

        //click to redirect to blood_oxygen_visuals
        blood_oxygen_btn.setOnClickListener {
            val intent = Intent(this, blood_oxygen_visualisation::class.java)
            intent.putIntegerArrayListExtra("bloodOxygenData", oxygenSaturationList)
            startActivity(intent)
        }

        //click to redirect to bodily_measurement_visuals
        bodily_measurement_btn.setOnClickListener {
            val intent = Intent(this, bodily_measurement_visualisation::class.java)
            intent.putIntegerArrayListExtra("weightData", weightList)
            intent.putIntegerArrayListExtra("bodyTempData", bodyTempList)
            intent.putIntegerArrayListExtra("bodyFatData", bodyFatList)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    val allHealthStats: LiveData<List<userHealthStatus>>
        get() = _healthStats

    private val _healthStats: MutableLiveData<List<userHealthStatus>> = MutableLiveData()

    fun getUserHealthStats(): MutableLiveData<List<userHealthStatus>> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userHealthStatus>> = api?.getAllStatus()!!
        call.enqueue(object: Callback<List<userHealthStatus>> {
            override fun onResponse(call: Call<List<userHealthStatus>>, response: Response<List<userHealthStatus>>) {
                if(response.isSuccessful) {
                    _healthStats.postValue(response.body())
                    val filterStats: List<userHealthStatus>? = response.body()?.filter {
                        it.date.before(endDate) &&
                        it.date.after(startDate) }
                    if(filterStats != null) {

                        for ((index, model) in filterStats.withIndex()) {
                            //Log.d("INDEX $index", "date ${model.heartRate}")
                            heartRateList?.add(model.heartRate)
                            bloodPressureList?.add(model.bloodPressure)
                            bodyTempList?.add(model.bodyTemp)
                            bloodGlucoseList?.add(model.bloodGlucose)
                            oxygenSaturationList?.add(model.oxygenSaturation)
                            heightList?.add(model.height)
                            weightList?.add(model.weight)
                            bodyFatList?.add(model.bodyFat)
                        }
                        Log.d(TAG, oxygenSaturationList.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<userHealthStatus>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _healthStats
    }


}