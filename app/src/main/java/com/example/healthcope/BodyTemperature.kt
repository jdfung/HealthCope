package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class BodyTemperature : AppCompatActivity() {
    lateinit var add_body_temp: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_temperature)

        val actionBar = supportActionBar
        actionBar!!.title = "Body Temperature"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_body_temp = findViewById((R.id.BT_button))

        add_body_temp.setOnClickListener {
            val Intent = Intent(this, BodyTemperatureAdd::class.java)
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