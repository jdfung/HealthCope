package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var to_vitals: Button
    lateinit var to_body_measurement: Button

    //nav bar button
    lateinit var to_manage_activity: TextView
    lateinit var to_learn_activity: TextView
    lateinit var to_profile_activity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

        to_vitals = findViewById<Button>(R.id.view_vital_button)
        to_body_measurement = findViewById<Button>(R.id.view_body_measure_button)

        to_manage_activity = findViewById<TextView>(R.id.homeManageNavOpt)
        to_learn_activity = findViewById<TextView>(R.id.homeLearnNavOpt)
        to_profile_activity = findViewById<TextView>(R.id.homeProfileNavOpt)

        to_vitals.setOnClickListener {
            val Intent = Intent(this, Vitals::class.java)
            startActivity(Intent)
        }

        to_body_measurement.setOnClickListener {
            val Intent = Intent(this, BodyMeasurements::class.java)
            startActivity(Intent)
        }


        to_manage_activity.setOnClickListener {
            val Intent = Intent(this, ManagePersonalHealthActivity::class.java)
            startActivity(Intent)
        }

        to_learn_activity.setOnClickListener {
            val Intent = Intent(this, LearnActivity::class.java)
            startActivity(Intent)
        }

        to_profile_activity.setOnClickListener {
            val Intent = Intent(this, User_Profile::class.java)
            startActivity(Intent)
        }
    }
}