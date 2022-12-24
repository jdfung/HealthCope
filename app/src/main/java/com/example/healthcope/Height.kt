package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class Height : AppCompatActivity() {
    lateinit var add_height: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        val actionBar = supportActionBar
        actionBar!!.title = "Height"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_height = findViewById((R.id.H_button))

        add_height.setOnClickListener {
            val Intent = Intent(this, HeightAdd::class.java)
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