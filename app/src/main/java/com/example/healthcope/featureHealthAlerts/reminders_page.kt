package com.example.healthcope.featureHealthAlerts

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.featureHealthAlerts.DialogWithData.Companion.TAG
import com.example.healthcope.R
import com.example.healthcope.viewModel.SharedViewModel
import com.example.healthcope.application.HealthCopeApplication
import com.example.healthcope.databinding.ActivityRemindersPageBinding
import com.example.healthcope.model.Reminders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class reminders_page : AppCompatActivity() {

    private val reminderViewModel: ReminderViewModel by viewModels{
        ReminderViewModelFactory((application as HealthCopeApplication).repository1)
    }

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: ActivityRemindersPageBinding
    lateinit var reminder_btn: ImageButton
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemindersPageBinding.inflate(layoutInflater)
        createNotificationChannel()
        val view = binding.root
        setContentView(view)

        val actionBar = supportActionBar
        actionBar!!.title = "Reminders"
        actionBar!!.setDisplayHomeAsUpEnabled(true)


        reminder_btn = findViewById<ImageButton>(R.id.btn_reminder)


        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        sharedViewModel.name.observe(this, Observer {
                val receiveData = it.toString()
                val splitData = receiveData.split("\n")
                val time = splitData[0].split(":")
                val hour = time[0]
                val minute = time[1]
                val newContact = Reminders(0, splitData[0] + "\n" + splitData[1])
                scheduleNotification(splitData[1], hour, minute)
                Log.d(TAG, hour + " " + minute)
                lifecycleScope.launch(Dispatchers.IO) {
                    reminderViewModel.addReminder(newContact)
                }
                val intent = Intent(this@reminders_page, reminders_page::class.java)
                startActivity(intent)
                finish()
        })

        val recyclerview = findViewById<RecyclerView>(R.id.list)

        reminderViewModel.allReminders.observe(this) {
                reminder ->
            recyclerview.layoutManager = LinearLayoutManager(this)
            val adapter = reminderAdapter()
            recyclerview.adapter = adapter
            adapter.submitList(reminder)

        }

        reminder_btn.setOnClickListener {
            DialogWithData().show(supportFragmentManager, DialogWithData.TAG)
        }


    }

    private fun scheduleNotification(drugList: String, hour: String, minute: String)
    {
        val intent = Intent(applicationContext, Notification::class.java)
        val title = "Reminder to take the following drugs."
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, drugList)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime(hour, minute)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        Toast.makeText(this, "Reminder Scheduled", Toast.LENGTH_LONG).show()
    }

    private fun getTime(hour: String, minute: String): Long
    {
        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
//        calendar.set(Calendar.MINUTE, minute.toInt())
//        calendar.set(Calendar.SECOND, 0)
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour.toInt(), minute.toInt())
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}