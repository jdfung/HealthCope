package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class OxygenSaturation : AppCompatActivity() {
    lateinit var add_oxygen_saturation: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oxygen_saturation)

        val actionBar = supportActionBar
        actionBar!!.title = "Oxygen Saturation"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_oxygen_saturation = findViewById((R.id.OS_button))

        add_oxygen_saturation.setOnClickListener {
            val Intent = Intent(this, OxygenSaturationAdd::class.java)
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