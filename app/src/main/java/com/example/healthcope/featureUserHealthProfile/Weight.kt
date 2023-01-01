package com.example.healthcope.featureUserHealthProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.example.healthcope.R

class Weight : AppCompatActivity() {
    lateinit var add_weight: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        val actionBar = supportActionBar
        actionBar!!.title = "Weight"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        add_weight = findViewById((R.id.W_button))

        add_weight.setOnClickListener {
            val Intent = Intent(this, WeightAdd::class.java)
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