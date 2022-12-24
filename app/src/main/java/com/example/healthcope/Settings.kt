package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton

class Settings : AppCompatActivity() {

    lateinit var notification_set: ImageButton
    lateinit var password_set: ImageButton
    lateinit var region_time_set: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val actionBar = supportActionBar
        actionBar!!.title = "Setting"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        notification_set = findViewById((R.id.notification_btn))
        password_set = findViewById((R.id.password_btn))
        region_time_set = findViewById((R.id.region_time_btn))

        notification_set.setOnClickListener {
            val Intent = Intent(this, Notifications::class.java)
            startActivity(Intent)
        }

        password_set.setOnClickListener {
            val Intent = Intent(this, PasswordSettings::class.java)
            startActivity(Intent)
        }

        region_time_set.setOnClickListener {
            val Intent = Intent(this, RegionAndTime::class.java)
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