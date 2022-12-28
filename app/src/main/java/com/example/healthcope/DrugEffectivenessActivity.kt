package com.example.healthcope

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import com.example.healthcope.application.SingletonApplication
import com.example.healthcope.model.DrugUsageRecord
import com.example.healthcope.model.DrugUsageRecordEntry

class DrugEffectivenessActivity : AppCompatActivity() {

    private val personalHealthLogViewModel: PersonalHealthLogViewModel by viewModels {
        PersonalHealthLogViewModelFactory((application as SingletonApplication).repositoryPersonalHealthLog)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_effectiveness)

        val actionBar = supportActionBar

        actionBar!!.title = "Drug Effectiveness"

        actionBar.setDisplayHomeAsUpEnabled(true)

        lateinit var drugUsageRecordCopy : DrugUsageRecord
        val heathLogID : Int = personalHealthLogViewModel.drugUsageRecordByID.value!!.healthLogID
        lateinit var drugUsageRecordEntriesCopy : MutableList<DrugUsageRecordEntry>

        val index = intent.getIntExtra("index", 0)

        val drugNameTitle : EditText = findViewById<EditText>(R.id.drugNameTitle)
        //val ratingVal : TextView = findViewById<TextView>(R.id.ratingVal)
        val ratingRadioGroup : RadioGroup = findViewById<RadioGroup>(R.id.drugUsageRating)
        lateinit var ratingRadioButton : RadioButton
        val noteEditTex : EditText = findViewById<EditText>(R.id.drugEffectivenessDescription)

        personalHealthLogViewModel.drugUsageRecordByID.observe(this){
            drugUsageRecord ->
            drugUsageRecordCopy = drugUsageRecord
            drugUsageRecordEntriesCopy = drugUsageRecord.drugUsageRecord

            drugNameTitle.setText(drugUsageRecordEntriesCopy[index].drugName)

            when (drugUsageRecordEntriesCopy[index].rating) {
                1 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_1)
                    ratingRadioButton.isChecked = true
                }
                2 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_2)
                    ratingRadioButton.isChecked = true
                }
                3 -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_3)
                    ratingRadioButton.isChecked = true
                }
                else -> {
                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_2)
                    ratingRadioButton.isChecked = true
                }
            }

            noteEditTex.setText(drugUsageRecordEntriesCopy[index].note)
        }

//        personalHealthLogViewModel.drugUsageRecordEntries.observe(this){
//            drugUsageRecordEntries ->
//
//            drugNameTitle.text = drugUsageRecordEntries[index].drugName
//
//            ratingVal.text = drugUsageRecordEntries[index].rating.toString()
//
//            when (ratingVal.text.toString().toInt()) {
//                1 -> {
//                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_1)
//                    ratingRadioButton.isChecked = true
//                }
//                2 -> {
//                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_2)
//                    ratingRadioButton.isChecked = true
//                }
//                3 -> {
//                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_3)
//                    ratingRadioButton.isChecked = true
//                }
//                else -> {
//                    ratingRadioButton = findViewById<RadioButton>(R.id.drugUsageRating_2)
//                    ratingRadioButton.isChecked = true
//                }
//            }
//
//            noteEditTex.setText(drugUsageRecordEntries[index].note)
//
//        }

        val saveBtn : Button = findViewById<Button>(R.id.drugEffectivenessSaveBtn)
        val discardBtn : Button = findViewById<Button>(R.id.drugEffectivenessDiscardBtn)

        saveBtn.setOnClickListener{
            val newDrugName : String = drugNameTitle.text.toString()
            val newRating : Int = findViewById<RadioButton>(ratingRadioGroup.checkedRadioButtonId).tooltipText.toString().toInt()
            val newNote : String = noteEditTex.text.toString()

            println("DEBUG " + findViewById<RadioButton>(ratingRadioGroup.checkedRadioButtonId).tooltipText.toString())

            if(newDrugName != drugUsageRecordEntriesCopy[index].drugName || newRating != drugUsageRecordEntriesCopy[index].rating || newNote != drugUsageRecordEntriesCopy[index].note){
                val drugUsageRecordEntryUpdate = DrugUsageRecordEntry(newDrugName, newRating, newNote)

                drugUsageRecordEntriesCopy[index] = drugUsageRecordEntryUpdate

                drugUsageRecordCopy.drugUsageRecord = drugUsageRecordEntriesCopy

                personalHealthLogViewModel.putDrugUsageRecordByID(heathLogID, drugUsageRecordCopy)

                Toast.makeText(this, "Saved successfully!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DrugEffectivenessActivity::class.java)
                intent.putExtra("index", index)
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
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}