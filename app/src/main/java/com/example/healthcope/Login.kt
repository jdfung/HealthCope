package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class Login : AppCompatActivity() {
    lateinit var register_direct: TextView
    lateinit var forgotpw_direct: TextView
    lateinit var login_btn: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register_direct = findViewById<TextView>(R.id.textViewRegister)
        forgotpw_direct = findViewById<TextView>(R.id.forgotPassword)
        login_btn = findViewById<AppCompatButton>(R.id.btnlogin)

        register_direct.setOnClickListener {
            val Intent = Intent(this, Register::class.java)
            startActivity(Intent)
        }

        forgotpw_direct.setOnClickListener {
            val Intent = Intent(this, Forgot_password::class.java)
            startActivity(Intent)
        }

        login_btn.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
    }
}