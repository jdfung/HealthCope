package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class Notifications : AppCompatActivity() {
    lateinit var save_setting: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val actionBar = supportActionBar
        actionBar!!.title = "Notifications"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        save_setting = findViewById((R.id.save_btn))

        save_setting.setOnClickListener {
            val Intent = Intent(this, Settings::class.java)
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