package com.example.healthcope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class BloodGlucoseAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_glucose_add)

        val actionBar = supportActionBar
        actionBar!!.title = "Add Blood Glucose"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
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