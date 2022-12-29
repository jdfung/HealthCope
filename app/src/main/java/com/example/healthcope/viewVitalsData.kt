package com.example.healthcope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.DialogWithData.Companion.TAG
import com.example.healthcope.model.userHealthStatus
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import com.google.android.material.datepicker.MaterialDatePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class viewVitalsData : AppCompatActivity() {
    lateinit var datePicker: LinearLayout
    lateinit var dateText: TextView
    lateinit var heartRateData: TextView
    lateinit var bloodPressureData: TextView
    lateinit var bodyTempData: TextView
    lateinit var bloodGlucoseData: TextView
    lateinit var oxygenSaturationData: TextView

    var pickedDate: Date? = Date(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond()*1000)

    private val _healthStats: MutableLiveData<List<userHealthStatus>> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_vitals_data)

        val actionBar = supportActionBar
        actionBar!!.title = "Vitals data"
        actionBar.setDisplayHomeAsUpEnabled(true)
        getUserHealthStats()

        datePicker = findViewById<LinearLayout>(R.id.viewDate)
        dateText = findViewById<TextView>(R.id.date)
        heartRateData = findViewById<TextView>(R.id.heartRateData)
        bloodPressureData = findViewById<TextView>(R.id.bloodPressureData)
        bodyTempData = findViewById<TextView>(R.id.bodyTempData)
        bloodGlucoseData = findViewById<TextView>(R.id.bloodGlucoseData)
        oxygenSaturationData = findViewById<TextView>(R.id.oxygenSaturationData)

        datePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                pickedDate = Date(datePicker.selection!!)
                dateText.text = pickedDate.toString()
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
    }

    fun getUserHealthStats(): MutableLiveData<List<userHealthStatus>> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userHealthStatus>> = api?.getAllStatus()!!
        call.enqueue(object: Callback<List<userHealthStatus>> {
            override fun onResponse(call: Call<List<userHealthStatus>>, response: Response<List<userHealthStatus>>) {
                if(response.isSuccessful) {
                    _healthStats.postValue(response.body())
                    val filterStats: List<userHealthStatus>? = response.body()?.filter {
                        it.date == pickedDate }
                    if(filterStats != null && filterStats.isNotEmpty()) {
                            Log.d(TAG, filterStats.toString())
                        for ((index, model) in filterStats.withIndex()) {
                            //Log.d("INDEX $index", "date ${model.heartRate}")
                            heartRateData.text = (model.heartRate.toString() + " BPM")
                            bloodPressureData.text = model.bloodPressure.toString() + " mmHg"
                            bloodGlucoseData.text = model.bloodGlucose.toString() + " mmol_l"
                            bodyTempData.text = model.bodyTemp.toString() + " °C"
                            oxygenSaturationData.text = model.oxygenSaturation.toString() + " %"
                        }
                    }
                    else
                    {
                        heartRateData.text = "-"
                        bloodPressureData.text = "-"
                        bloodGlucoseData.text = "-"
                        bodyTempData.text = "-"
                        oxygenSaturationData.text = "-"
                    }
                }
            }

            override fun onFailure(call: Call<List<userHealthStatus>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _healthStats
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}