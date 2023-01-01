package com.example.healthcope.featureTrackAndCheckIn

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import com.example.healthcope.PersonalHealthLogViewModel
import com.example.healthcope.PersonalHealthLogViewModelFactory
import com.example.healthcope.R
import com.example.healthcope.application.HealthCopeApplication
import com.example.healthcope.model.PhysicalConditionRecordEntry

class PhysicalConditionActivity : AppCompatActivity() {

    private val personalHealthLogViewModel: PersonalHealthLogViewModel by viewModels {
        PersonalHealthLogViewModelFactory((application as HealthCopeApplication).repositoryPersonalHealthLog)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical_condition)

        val actionBar = supportActionBar

        actionBar!!.title = "Physical Condition"

        actionBar.setDisplayHomeAsUpEnabled(true)

        lateinit var physicalConditionRecordEntryCopy : PhysicalConditionRecordEntry
        val heathLogID : Int = personalHealthLogViewModel.physicalConditionRecordEntryByID.value!!.healthLogID

        val ratingRadioGroup : RadioGroup = findViewById<RadioGroup>(R.id.physicalConditionRating)
        lateinit var ratingRadioButton : RadioButton
        val noteEditText : EditText = findViewById<EditText>(R.id.physicalConditionNoteEditText)

        personalHealthLogViewModel.physicalConditionRecordEntryByID.observe(this){
                physicalConditionRecordEntry ->

            physicalConditionRecordEntryCopy = physicalConditionRecordEntry

            when (physicalConditionRecordEntryCopy.rating) {
                1 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.physicalConditionRating_1)
                    ratingRadioButton.isChecked = true
                }
                2 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.physicalConditionRating_2)
                    ratingRadioButton.isChecked = true
                }
                3 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.physicalConditionRating_3)
                    ratingRadioButton.isChecked = true
                }
                4 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.physicalConditionRating_4)
                    ratingRadioButton.isChecked = true
                }
                5 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.physicalConditionRating_5)
                    ratingRadioButton.isChecked = true
                }
                else -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.physicalConditionRating_3)
                    ratingRadioButton.isChecked = true
                }
            }

            noteEditText.setText(physicalConditionRecordEntryCopy.note)
        }

        val saveBtn : Button = findViewById<Button>(R.id.physicalConditionSaveBtn)
        val discardBtn : Button = findViewById<Button>(R.id.physicalConditionDiscardBtn)

        saveBtn.setOnClickListener{
            val newRating : Int = findViewById<RadioButton>(ratingRadioGroup.checkedRadioButtonId).tooltipText.toString().toInt()
            val newNote : String = noteEditText.text.toString()

            if(newRating != physicalConditionRecordEntryCopy.rating || newNote != physicalConditionRecordEntryCopy.note){
                val physicalConditionRecordEntryUpdate = PhysicalConditionRecordEntry(heathLogID, newRating, newNote)

                personalHealthLogViewModel.putPhysicalConditionRecordEntryByID(heathLogID, physicalConditionRecordEntryUpdate)

                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, PhysicalConditionActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show()
            }
        }

        discardBtn.setOnClickListener{
            onBackPressed()
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}