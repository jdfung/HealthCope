package com.example.healthcope.featureUserManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.healthcope.R
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class Edit_profile : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var genderEdit: EditText
    lateinit var dobEdit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val actionBar = supportActionBar
        actionBar!!.title = "Edit Profile"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        genderEdit = findViewById<EditText>(R.id.edit_gender)
        dobEdit = findViewById<EditText>(R.id.edit_dob)

        //call alertdialog for gender selection
        genderEdit.setOnClickListener {
            val listItems = arrayOf("Male", "Female", "Prefer to not specify")
            val mBuilder = AlertDialog.Builder(this, R.style.MyDialogTheme)
            mBuilder.setTitle("Select Gender")
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                genderEdit.setText(listItems[i])
                dialogInterface.dismiss()
            }
            // Set the neutral/cancel button click listener
            mBuilder.setNeutralButton("Cancel") { dialog, which ->
                // Do something when click the neutral button
                dialog.cancel()
            }

            val mDialog = mBuilder.create()
            mDialog.show()
        }

        dobEdit.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                // formatting date in dd-mm-yyyy format.
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                dobEdit.setText(date)

            }

            // Setting up the event for when cancelled is clicked
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }

            // Setting up the event for when back button is pressed
            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }
    }
}