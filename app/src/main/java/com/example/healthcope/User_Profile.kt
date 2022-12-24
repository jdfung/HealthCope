package com.example.healthcope

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class User_Profile : AppCompatActivity() {
    lateinit var edit_profile_button: Button
    lateinit var health_report_button: Button
    lateinit var emergency_contact_button: Button
    lateinit var reminders_button: Button
    lateinit var to_home_activity: TextView
    lateinit var to_manage_activity: TextView
    lateinit var to_learn_activity: TextView

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        edit_profile_button = findViewById<Button>(R.id.btn_edit_profile)
        health_report_button = findViewById<Button>(R.id.open_health_report_btn)
        emergency_contact_button = findViewById<Button>(R.id.open_emergency_contact_btn)
        reminders_button = findViewById<Button>(R.id.open_reminders_btn)
        to_home_activity = findViewById<TextView>(R.id.profileHomeNavOpt)
        to_manage_activity = findViewById<TextView>(R.id.profileManageNavOpt)
        to_learn_activity = findViewById<TextView>(R.id.profileLearnNavOpt)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)

        edit_profile_button.setOnClickListener {
            val Intent = Intent(this, Edit_profile::class.java)
            startActivity(Intent)
        }

        health_report_button.setOnClickListener {
            val Intent = Intent(this, ongoing_health_report_page::class.java)
            startActivity(Intent)
        }

        emergency_contact_button.setOnClickListener {
            val Intent = Intent(this, add_emergency_contacts::class.java)
            startActivity(Intent)
        }

        reminders_button.setOnClickListener {
            val Intent = Intent(this, reminders_page::class.java)
            startActivity(Intent)
        }

        to_home_activity.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings -> {
                val Intent = Intent(this, Settings::class.java)
                startActivity(Intent)
            }
            R.id.logout -> {
                val Intent = Intent(this, Login::class.java)
                startActivity(Intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}