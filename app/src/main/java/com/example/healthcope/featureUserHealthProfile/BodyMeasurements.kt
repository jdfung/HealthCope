package com.example.healthcope.featureUserHealthProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import com.example.healthcope.R

class BodyMeasurements : AppCompatActivity() {
    lateinit var view_measurement_data: ImageButton
    lateinit var add_measurement_data: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_measurements)

        val actionBar = supportActionBar
        actionBar!!.title = "Body Measurements"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        view_measurement_data = findViewById<ImageButton>(R.id.viewMeasurement_btn)
        add_measurement_data = findViewById<ImageButton>(R.id.add_body_measurement_btn)

        view_measurement_data.setOnClickListener {
            val Intent = Intent(this, viewBodyMeasurementData::class.java)
            startActivity(Intent)
        }

        add_measurement_data.setOnClickListener {
            val Intent = Intent(this, addBodyMeasurementData::class.java)
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