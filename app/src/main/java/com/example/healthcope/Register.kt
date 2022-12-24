package com.example.healthcope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class Register : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}