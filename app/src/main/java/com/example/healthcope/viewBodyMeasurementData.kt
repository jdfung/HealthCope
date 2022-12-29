package com.example.healthcope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
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

class viewBodyMeasurementData : AppCompatActivity() {
    lateinit var datePicker: LinearLayout
    lateinit var dateText: TextView
    lateinit var heightData: TextView
    lateinit var weightData: TextView
    lateinit var bodyFatData: TextView

    var pickedDate: Date? = Date(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond()*1000)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_body_measurement_data)
        getUserHealthStats()

        val actionBar = supportActionBar
        actionBar!!.title = "Body measurement data"
        actionBar.setDisplayHomeAsUpEnabled(true)

        datePicker = findViewById<LinearLayout>(R.id.viewDate)
        dateText = findViewById<TextView>(R.id.date)
        heightData = findViewById<TextView>(R.id.heightData)
        weightData = findViewById<TextView>(R.id.weightData)
        bodyFatData = findViewById<TextView>(R.id.bodyFatData)

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

    fun getUserHealthStats(){
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userHealthStatus>> = api?.getAllStatus()!!
        call.enqueue(object: Callback<List<userHealthStatus>> {
            override fun onResponse(call: Call<List<userHealthStatus>>, response: Response<List<userHealthStatus>>) {
                if(response.isSuccessful) {
                    val filterStats: List<userHealthStatus>? = response.body()?.filter {
                        it.date == pickedDate }
                    if(filterStats != null && filterStats.isNotEmpty()) {
                        Log.d(DialogWithData.TAG, filterStats.toString())
                        for ((index, model) in filterStats.withIndex()) {
                            //Log.d("INDEX $index", "date ${model.heartRate}")
                            heightData.text = model.height.toString() + " cm"
                            weightData.text = model.weight.toString() + " kg"
                            bodyFatData.text = model.bodyFat.toString() + " %"
                        }
                    }
                    else
                    {
                        heightData.text = "-"
                        weightData.text = "-"
                        bodyFatData.text = "-"
                    }
                }
            }

            override fun onFailure(call: Call<List<userHealthStatus>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

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