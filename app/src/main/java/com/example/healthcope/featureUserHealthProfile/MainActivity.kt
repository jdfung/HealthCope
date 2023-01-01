package com.example.healthcope.featureUserHealthProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.healthcope.*
import com.example.healthcope.featureHealthInformation.LearnActivity
import com.example.healthcope.featureTrackAndCheckIn.ManagePersonalHealthActivity
import com.example.healthcope.featureUserManagement.User_Profile

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
        actionBar?.hide()

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

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}