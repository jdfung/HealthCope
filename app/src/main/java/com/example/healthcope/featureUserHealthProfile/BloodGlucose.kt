package com.example.healthcope.featureUserHealthProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.example.healthcope.R

class BloodGlucose : AppCompatActivity() {
    lateinit var add_blood_glucose: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_glucose)

        val actionBar = supportActionBar
        actionBar!!.title = "Blood Glucose"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_blood_glucose = findViewById((R.id.BG_button))

        add_blood_glucose.setOnClickListener {
            val Intent = Intent(this, BloodGlucoseAdd::class.java)
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