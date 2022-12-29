package com.example.healthcope

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class Forgot_password : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var forgotBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val actionBar = supportActionBar
        actionBar!!.title = "Edit Profile"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)

        forgotBtn = findViewById(R.id.btnForgot)

        forgotBtn.setOnClickListener {
            basicAlert("A reset password link has been sent to your email. Kindly check your mailbox.")
        }
    }

    fun basicAlert(message: String){

        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = {
                dialog: DialogInterface, which: Int ->
        }

        with(builder)
        {
            setTitle("Reset Password")
            setMessage(message)
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }


    }
}