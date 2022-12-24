package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.google.android.material.datepicker.MaterialDatePicker

class ongoing_health_report_page : AppCompatActivity() {
    lateinit var datePicker: LinearLayout
    lateinit var heart_rate_btn: Button
    lateinit var blood_pressure_btn: Button
    lateinit var blood_oxygen_btn: Button
    lateinit var bodily_measurement_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ongoing_health_report_page)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        datePicker = findViewById<LinearLayout>(R.id.health_report_date)
        heart_rate_btn = findViewById<Button>(R.id.to_heart_rate_visuals)
        blood_pressure_btn = findViewById<Button>(R.id.to_blood_pressure_visuals)
        blood_oxygen_btn = findViewById<Button>(R.id.to_blood_oxygen_visuals)
        bodily_measurement_btn = findViewById<Button>(R.id.to_bodily_measure_visuals)

        //click to show data picker
        datePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
        }

        //click to redirect to heart_rate_visuals
        heart_rate_btn.setOnClickListener {
            val intent = Intent(this, heart_rate_visualization_page::class.java)
            startActivity(intent)
        }

        //click to redirect to blood_pressure_visuals
        blood_pressure_btn.setOnClickListener {
            val intent = Intent(this, blood_pressure_visualisation::class.java)
            startActivity(intent)
        }

        //click to redirect to blood_oxygen_visuals
        blood_oxygen_btn.setOnClickListener {
            val intent = Intent(this, blood_oxygen_visualisation::class.java)
            startActivity(intent)
        }

        //click to redirect to bodily_measurement_visuals
        bodily_measurement_btn.setOnClickListener {
            val intent = Intent(this, bodily_measurement_visualisation::class.java)
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
}