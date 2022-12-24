package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class BodyMeasurements : AppCompatActivity() {
    lateinit var to_height: ImageButton
    lateinit var to_weight: ImageButton
    lateinit var to_body_fat: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_measurements)

        val actionBar = supportActionBar
        actionBar!!.title = "Body Measurements"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        to_height = findViewById<ImageButton>(R.id.height_btn)
        to_weight = findViewById<ImageButton>(R.id.weight_btn)
        to_body_fat = findViewById<ImageButton>(R.id.body_fat_percent_btn)

        to_height.setOnClickListener {
            val Intent = Intent(this, Height::class.java)
            startActivity(Intent)
        }

        to_weight.setOnClickListener {
            val Intent = Intent(this, Weight::class.java)
            startActivity(Intent)
        }

        to_body_fat.setOnClickListener {
            val Intent = Intent(this, BodyFatPercentage::class.java)
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