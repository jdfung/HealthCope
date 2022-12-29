package com.example.healthcope

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.format.DateUtils
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.healthcope.model.userAccount
import com.example.healthcope.model.userHealthStatus
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class addVitalsData : AppCompatActivity() {
    lateinit var datePicker: LinearLayout
    lateinit var dateText: TextView
    lateinit var heartRateInput: EditText
    lateinit var bloodPressureInput: EditText
    lateinit var bodyTempInput: EditText
    lateinit var bloodGlucoseInput: EditText
    lateinit var oxygenSaturationInput: EditText
    lateinit var addVitals: Button

    var pickedDate: Date? = Date(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toEpochSecond()*1000)
    var height: Int = 0
    var weight: Int = 0
    var bodyFat: Int = 0
    var updateID: Int? = 0
    private val _healthStats: MutableLiveData<List<userHealthStatus>> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vitals_data)
        addOrUpdate()

        val actionBar = supportActionBar
        actionBar!!.title = "Add new vitals data"
        actionBar.setDisplayHomeAsUpEnabled(true)

        datePicker = findViewById<LinearLayout>(R.id.viewDate)
        dateText = findViewById<TextView>(R.id.date)
        heartRateInput = findViewById<EditText>(R.id.heartRateInput)
        bloodPressureInput = findViewById<EditText>(R.id.bloodPressureInput)
        bodyTempInput = findViewById<EditText>(R.id.bodyTempInput)
        bloodGlucoseInput = findViewById<EditText>(R.id.bloodGlucoseInput)
        oxygenSaturationInput = findViewById<EditText>(R.id.oxygenSaturationInput)
        addVitals = findViewById<Button>(R.id.vitals_add_btn)

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

        addVitals.setOnClickListener {

            if(heartRateInput.text.isNullOrEmpty())
            {
                heartRateInput.setError("This field is required")
            }
            else if(bloodPressureInput.text.isNullOrEmpty()){
                bloodPressureInput.setError("This field is required")
            }
            else if(bodyTempInput.text.isNullOrEmpty()){
                bodyTempInput.setError("This field is required")
            }
            else if(bloodGlucoseInput.text.isNullOrEmpty()){
                bloodGlucoseInput.setError("This field is required")
            }
            else if(oxygenSaturationInput.text.isNullOrEmpty()){
                oxygenSaturationInput.setError("This field is required")
            }
            else
            {
                Log.d("test", pickedDate.toString())
                val date = pickedDate
                val heartRate = heartRateInput.text.toString().toInt()
                val bloodPressure = bloodPressureInput.text.toString().toInt()
                val bodyTemp = bodyTempInput.text.toString().toInt()
                val bloodGlucose = bloodGlucoseInput.text.toString().toInt()
                val oxygenSaturation = oxygenSaturationInput.text.toString().toInt()
                val newVitals = userHealthStatus(
                    0,
                    date!!,
                    heartRate,
                    bloodPressure,
                    bodyTemp,
                    bloodGlucose,
                    oxygenSaturation,
                    height,
                    weight,
                    bodyFat
                )
                if(updateID!=null)
                {
                    Log.d("update", updateID.toString())
                    updateVitalsData(updateID!!, newVitals)
                }
                else
                {
                    Log.d("add", updateID.toString())
                    addVitalsData(newVitals)
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
                    _healthStats.postValue(response.body())
                    val filterStats: List<userHealthStatus>? = response.body()?.filter {
                        it.date == pickedDate }
                    if(filterStats != null && filterStats.isNotEmpty()) {
                        Toast.makeText(this@addVitalsData, "Already has record of this date", Toast.LENGTH_LONG).show()
                       for ((index, model) in filterStats.withIndex()) {

                            updateID = model.id
                            height = model.height
                            weight = model.weight
                            bodyFat = model.bodyFat
                            heartRateInput.setText(model.heartRate.toString())
                            bloodPressureInput.setText(model.bloodPressure.toString())
                            bloodGlucoseInput.setText(model.bloodGlucose.toString())
                            bodyTempInput.setText(model.bodyTemp.toString())
                            oxygenSaturationInput.setText(model.oxygenSaturation.toString())

                        }
                    }
                    else
                    {
                        updateID = null
                        heartRateInput.setText("")
                        bloodPressureInput.setText("")
                        bloodGlucoseInput.setText("")
                        bodyTempInput.setText("")
                        oxygenSaturationInput.setText("")
                    }

                }
            }

            override fun onFailure(call: Call<List<userHealthStatus>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })
    }

    fun addVitalsData(status: userHealthStatus): LiveData<userHealthStatus> {
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

    fun updateVitalsData(id: Int, status: userHealthStatus): LiveData<userHealthStatus> {
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
