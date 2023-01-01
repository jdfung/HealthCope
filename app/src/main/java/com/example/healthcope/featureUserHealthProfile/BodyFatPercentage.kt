package com.example.healthcope.featureUserHealthProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.example.healthcope.R

class BodyFatPercentage : AppCompatActivity() {
    lateinit var add_body_fat: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_fat_percentage)

        val actionBar = supportActionBar
        actionBar!!.title = "Body Fat Percentage"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_body_fat = findViewById((R.id.BFP_button))

        add_body_fat.setOnClickListener {
            val Intent = Intent(this, BodyFatPercentageAdd::class.java)
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