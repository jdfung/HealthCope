package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class PasswordSettings : AppCompatActivity() {
    lateinit var reset_pw: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_settings)

        val actionBar = supportActionBar
        actionBar!!.title = "Password Settings"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        reset_pw = findViewById((R.id.continue_btn))

        reset_pw.setOnClickListener {
            val Intent = Intent(this, ResetPassword::class.java)
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