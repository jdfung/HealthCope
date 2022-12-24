package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class ResetPassword : AppCompatActivity() {
    lateinit var reset_pw: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        reset_pw = findViewById((R.id.close_btn))

        reset_pw.setOnClickListener {
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