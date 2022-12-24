package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class Vitals : AppCompatActivity() {

    lateinit var to_heart_rate: ImageButton
    lateinit var to_blood_pressure: ImageButton
    lateinit var to_body_temp: ImageButton
    lateinit var to_blood_glucose: ImageButton
    lateinit var to_oxygen_saturation: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals)

        val actionBar = supportActionBar
        actionBar!!.title = "Vitals"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        to_heart_rate = findViewById<ImageButton>(R.id.heart_rate_btn)
        to_blood_pressure = findViewById<ImageButton>(R.id.blood_pressure_btn)
        to_body_temp = findViewById<ImageButton>(R.id.body_temperature_btn)
        to_blood_glucose = findViewById<ImageButton>(R.id.blood_glucose_btn)
        to_oxygen_saturation = findViewById<ImageButton>(R.id.oxygen_saturation_btn)

        to_heart_rate.setOnClickListener {
            val Intent = Intent(this, HeartRate::class.java)
            startActivity(Intent)
        }

        to_blood_pressure.setOnClickListener {
            val Intent = Intent(this, BloodPressure::class.java)
            startActivity(Intent)
        }

        to_body_temp.setOnClickListener {
            val Intent = Intent(this, BodyTemperature::class.java)
            startActivity(Intent)
        }

        to_blood_glucose.setOnClickListener {
            val Intent = Intent(this, BloodGlucose::class.java)
            startActivity(Intent)
        }

        to_oxygen_saturation.setOnClickListener {
            val Intent = Intent(this, OxygenSaturation::class.java)
            startActivity(Intent)
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
}