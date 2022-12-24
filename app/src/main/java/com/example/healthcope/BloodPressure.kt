package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class BloodPressure : AppCompatActivity() {
    lateinit var add_blood_pressure: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_pressure)

        val actionBar = supportActionBar
        actionBar!!.title = "Blood Pressure"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_blood_pressure = findViewById((R.id.BP_button))

        add_blood_pressure.setOnClickListener {
            val Intent = Intent(this, BloodPressureAdd::class.java)
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