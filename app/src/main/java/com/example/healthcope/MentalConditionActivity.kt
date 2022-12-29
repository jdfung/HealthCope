package com.example.healthcope

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import com.example.healthcope.application.HealthCopeApplication
import com.example.healthcope.model.MentalConditionRecordEntry

class MentalConditionActivity : AppCompatActivity() {

    private val personalHealthLogViewModel: PersonalHealthLogViewModel by viewModels {
        PersonalHealthLogViewModelFactory((application as HealthCopeApplication).repositoryPersonalHealthLog)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mental_condition)

        val actionBar = supportActionBar

        actionBar!!.title = "Mental Condition"

        actionBar.setDisplayHomeAsUpEnabled(true)

        lateinit var mentalConditionRecordEntryCopy : MentalConditionRecordEntry
        val heathLogID : Int = personalHealthLogViewModel.mentalConditionRecordEntryByID.value!!.healthLogID

        val ratingRadioGroup : RadioGroup = findViewById<RadioGroup>(R.id.mentalConditionRating)
        lateinit var ratingRadioButton : RadioButton
        val dairyEditText : EditText = findViewById<EditText>(R.id.personalDiaryEditText)

        personalHealthLogViewModel.mentalConditionRecordEntryByID.observe(this){
            mentalConditionRecordEntry ->

            mentalConditionRecordEntryCopy = mentalConditionRecordEntry

            when (mentalConditionRecordEntryCopy.rating) {
                1 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.mentalConditionRating_1)
                    ratingRadioButton.isChecked = true
                }
                2 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.mentalConditionRating_2)
                    ratingRadioButton.isChecked = true
                }
                3 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.mentalConditionRating_3)
                    ratingRadioButton.isChecked = true
                }
                4 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.mentalConditionRating_4)
                    ratingRadioButton.isChecked = true
                }
                5 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.mentalConditionRating_5)
                    ratingRadioButton.isChecked = true
                }
                else -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.mentalConditionRating_3)
                    ratingRadioButton.isChecked = true
                }
            }

            dairyEditText.setText(mentalConditionRecordEntryCopy.dairy)
        }

        val saveBtn : Button = findViewById<Button>(R.id.mentalConditionSaveBtn)
        val discardBtn : Button = findViewById<Button>(R.id.mentalConditionDiscardBtn)

        saveBtn.setOnClickListener{
            val newRating : Int = findViewById<RadioButton>(ratingRadioGroup.checkedRadioButtonId).tooltipText.toString().toInt()
            val newDairy : String = dairyEditText.text.toString()

            if(newRating != mentalConditionRecordEntryCopy.rating || newDairy != mentalConditionRecordEntryCopy.dairy){
                val mentalConditionRecordEntryUpdate = MentalConditionRecordEntry(heathLogID, newRating, newDairy)

                personalHealthLogViewModel.putMentalConditionRecordEntryByID(heathLogID, mentalConditionRecordEntryUpdate)

                val intent = Intent(this, MentalConditionActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()
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