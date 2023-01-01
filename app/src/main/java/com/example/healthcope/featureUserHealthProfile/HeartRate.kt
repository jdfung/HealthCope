package com.example.healthcope.featureUserHealthProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.example.healthcope.R

class HeartRate : AppCompatActivity() {

    lateinit var add_heart_rate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_rate)

        val actionBar = supportActionBar
        actionBar!!.title = "Heart Rate"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_heart_rate = findViewById((R.id.HR_button))

        add_heart_rate.setOnClickListener {
            val Intent = Intent(this, HeartRateAdd::class.java)
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