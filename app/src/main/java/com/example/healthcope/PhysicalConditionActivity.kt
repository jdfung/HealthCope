package com.example.healthcope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class PhysicalConditionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical_condition)

        val actionBar = supportActionBar

        actionBar!!.title = "Physical Condition"

        actionBar.setDisplayHomeAsUpEnabled(true)

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