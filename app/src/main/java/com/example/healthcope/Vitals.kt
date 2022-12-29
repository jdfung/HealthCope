package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class Vitals : AppCompatActivity() {

    lateinit var to_view_vitals: ImageButton
    lateinit var to_add_vitals: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitals)

        val actionBar = supportActionBar
        actionBar!!.title = "Vitals"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        to_view_vitals = findViewById<ImageButton>(R.id.viewVitals_btn)
        to_add_vitals = findViewById<ImageButton>(R.id.add_vitals_btn)

        to_view_vitals.setOnClickListener {
            val Intent = Intent(this, viewVitalsData::class.java)
            startActivity(Intent)
        }

        to_add_vitals.setOnClickListener {
            val Intent = Intent(this, addVitalsData::class.java)
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