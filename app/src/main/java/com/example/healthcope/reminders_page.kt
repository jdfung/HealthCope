package com.example.healthcope

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.healthcope.databinding.ActivityRemindersPageBinding

class reminders_page : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: ActivityRemindersPageBinding
    lateinit var reminder_btn: ImageButton
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemindersPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actionBar = supportActionBar
        actionBar!!.title = "Reminders"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val myArrayList = ArrayList<String>()

        myArrayList.add("3:12 \n Morphine, Aspirin")
        myArrayList.add("13:00 \n Antibiotic, Prasugrel")

        var mListView = findViewById<ListView>(R.id.list)
        reminder_btn = findViewById<ImageButton>(R.id.btn_reminder)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myArrayList)
        mListView.adapter = arrayAdapter

        sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]
        sharedViewModel.name.observe(this, Observer {
            myArrayList.add(it.toString())
        })


        reminder_btn.setOnClickListener {
            DialogWithData().show(supportFragmentManager, DialogWithData.TAG)
        }


    }

}