package com.example.healthcope.featureAppSettings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.healthcope.R
import com.example.healthcope.featureUserHealthProfile.MainActivity

class ResetPassword : AppCompatActivity() {
    lateinit var reset_pw: Button
    lateinit var reset_pw_detail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        reset_pw = findViewById((R.id.close_btn))
        reset_pw_detail = findViewById(R.id.reset_password_details)
        val emailAddress: String? = intent.getStringExtra("emailAddress")
        reset_pw_detail.text =
            """A reset password link has been sent to your email. Kindly check your mailbox in: $emailAddress"""

        reset_pw.setOnClickListener {
            val Intent = Intent(this, MainActivity::class.java)
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