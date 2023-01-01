package com.example.healthcope.featureAppSettings

import android.content.ComponentName
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import com.example.healthcope.R
import com.example.healthcope.featureHealthAlerts.Notification

class Notifications : AppCompatActivity() {
    lateinit var save_setting: Button
    lateinit var notifSwitch: Switch
    var reminderStatus: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        val component = ComponentName(applicationContext, Notification::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val actionBar = supportActionBar
        actionBar!!.title = "Notifications"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        save_setting = findViewById((R.id.save_btn))
        notifSwitch = findViewById(R.id.reminder_switch)
        notifSwitch.isChecked = ReceiverEnabledStatus(component)
        notifSwitch.setOnCheckedChangeListener{_, isChecked ->
            reminderStatus = isChecked
        }
        save_setting.setOnClickListener {
//            val Intent = Intent(this, Settings::class.java)
//            startActivity(Intent)

            if(reminderStatus)
            {
                packageManager.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
            }
            else
            {
                packageManager.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
            }
            Toast.makeText(this@Notifications,"Notification setting saved successfully", Toast.LENGTH_LONG).show()
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

    fun ReceiverEnabledStatus(component: ComponentName): Boolean
    {
        if(packageManager.getComponentEnabledSetting(component).toString() == "1")
        {
            return true
        }
        else
        {
            return false
        }
    }
}