package com.example.healthcope

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.model.userHealthStatus
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class addBodyMeasurementData : AppCompatActivity() {
    lateinit var datePicker: LinearLayout
    lateinit var dateText: TextView
    lateinit var heightInput: EditText
    lateinit var weightInput: EditText
    lateinit var bodyFatInput: EditText
    lateinit var addMeasurement: Button

    var pickedDate: Date? = Date(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond()*1000)
    var heartRate: Int = 0
    var bloodPress: Int = 0
    var bodyTemp: Int = 0
    var bloodGlucose: Int = 0
    var oxygenSats: Int = 0
    var updateID: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_body_measurement_data)
        addOrUpdate()

        val actionBar = supportActionBar
        actionBar!!.title = "Add new body measurement data"
        actionBar.setDisplayHomeAsUpEnabled(true)

        datePicker = findViewById<LinearLayout>(R.id.viewDate)
        dateText = findViewById<TextView>(R.id.date)
        heightInput = findViewById<EditText>(R.id.heightInput)
        weightInput = findViewById<EditText>(R.id.weightInput)
        bodyFatInput = findViewById<EditText>(R.id.bodyFatInput)
        addMeasurement = findViewById<Button>(R.id.measurement_add_btn)

        datePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                pickedDate = Date(datePicker.selection!!)
                dateText.text = pickedDate.toString()
                addOrUpdate()

            }
            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG)
                    .show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }

        addMeasurement.setOnClickListener {

            if(heightInput.text.isNullOrEmpty())
            {
                heightInput.setError("This field is required")
            }
            else if(weightInput.text.isNullOrEmpty()){
                weightInput.setError("This field is required")
            }
            else if(bodyFatInput.text.isNullOrEmpty()){
                bodyFatInput.setError("This field is required")
            }
            else
            {
                Log.d("test", pickedDate.toString())
                val date = pickedDate
                val height = heightInput.text.toString().toInt()
                val weight = weightInput.text.toString().toInt()
                val bodyFat = bodyFatInput.text.toString().toInt()
                val newBodyMeasurements = userHealthStatus(
                    0,
                    date!!,
                    heartRate,
                    bloodPress,
                    bodyTemp,
                    bloodGlucose,
                    oxygenSats,
                    height,
                    weight,
                    bodyFat
                )
                if(updateID!=null)
                {
                    Log.d("update", updateID.toString())
                    updateMeasurementData(updateID!!, newBodyMeasurements)
                }
                else
                {
                    Log.d("add", updateID.toString())
                    addMeasurementData(newBodyMeasurements)
                }
            }

        }
    }

    fun addOrUpdate() {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userHealthStatus>> = api?.getAllStatus()!!
        call.enqueue(object: Callback<List<userHealthStatus>> {
            override fun onResponse(call: Call<List<userHealthStatus>>, response: Response<List<userHealthStatus>>) {
                if(response.isSuccessful) {
                    val filterStats: List<userHealthStatus>? = response.body()?.filter {
                        it.date == pickedDate }
                    if(filterStats != null && filterStats.isNotEmpty()) {
                        Toast.makeText(this@addBodyMeasurementData, "Already has record of this date", Toast.LENGTH_LONG).show()
                        for ((index, model) in filterStats.withIndex()) {

                            updateID = model.id
                            heartRate = model.heartRate
                            bloodPress = model.bloodPressure
                            bodyTemp = model.bodyTemp
                            bloodGlucose = model.bloodGlucose
                            oxygenSats = model.oxygenSaturation
                            heightInput.setText(model.height.toString())
                            weightInput.setText(model.weight.toString())
                            bodyFatInput.setText(model.bodyFat.toString())
                        }
                    }
                    else
                    {
                        updateID = null
                        heightInput.setText("")
                        weightInput.setText("")
                        bodyFatInput.setText("")
                    }

                }
            }

            override fun onFailure(call: Call<List<userHealthStatus>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })
    }

    fun addMeasurementData(status: userHealthStatus): LiveData<userHealthStatus> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val data: MutableLiveData<userHealthStatus> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<userHealthStatus> = api?.addHealthStatus(status)!!
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.value = response.body()
                    basicAlert("Data added successfully")
                }
            }
        }
        return data
    }

    fun updateMeasurementData(id: Int, status: userHealthStatus): LiveData<userHealthStatus> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val data: MutableLiveData<userHealthStatus> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<userHealthStatus> = api?.updateHealthStatus(id, status)!!
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.value = response.body()
                    basicAlert("Data updated successfully")
                }
            }
        }
        return data
    }

    fun basicAlert(message: String){

        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = {
                dialog: DialogInterface, which: Int ->

        }

        with(builder)
        {
            setTitle("Data addition status")
            setMessage(message)
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }
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