package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.application.SingletonApplication
import com.example.healthcope.model.DrugUsageRecord
import com.example.healthcope.model.DrugUsageRecordEntry

class DrugUsageActivity : AppCompatActivity(), DrugListAdapter.DrugListOnItemClickListener {

    private val personalHealthLogViewModel : PersonalHealthLogViewModel by viewModels<PersonalHealthLogViewModel> {
        PersonalHealthLogViewModelFactory((application as SingletonApplication).repositoryPersonalHealthLog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_usage)

        val actionBar = supportActionBar

        actionBar!!.title = "Drug Usage"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val rv = findViewById<RecyclerView>(R.id.drugListView)
        //val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        lateinit var drugUsageRecordCopy : DrugUsageRecord
        val heathLogID : Int = personalHealthLogViewModel.drugUsageRecordByID.value!!.healthLogID
        lateinit var drugUsageRecordEntriesCopy : MutableList<DrugUsageRecordEntry>

        personalHealthLogViewModel.drugUsageRecordByID.observe(this) {
                drugUsageRecord ->
            drugUsageRecordCopy = drugUsageRecord
            drugUsageRecordEntriesCopy = drugUsageRecord.drugUsageRecord

            rv.layoutManager = LinearLayoutManager(this)
            val adapter = DrugListAdapter(this)
            rv.adapter = adapter
            adapter.submitList(drugUsageRecordEntriesCopy)
            //progressBar.visibility = View.GONE
            //rv.visibility = View.VISIBLE
        }

        val addNewDrugButton : Button = findViewById<Button>(R.id.addNewDrugBtn)

        addNewDrugButton.setOnClickListener{
//            val drugUsageRecord : DrugUsageRecord = personalHealthLogViewModel.drugUsageRecord.value!!
//            val heathLogID : Int = drugUsageRecord.healthLogID
//            val drugUsageRecordEntries : MutableList<DrugUsageRecordEntry> = drugUsageRecord.drugUsageRecord

            val addDrugEditText : EditText = findViewById<EditText>(R.id.addDrugEditText)

            val drugName : String = addDrugEditText.text.toString()

            if(drugName != ""){
                val newDrugUsageRecordEntry = DrugUsageRecordEntry(drugName, 2, "")
                drugUsageRecordEntriesCopy.add(newDrugUsageRecordEntry)

                drugUsageRecordCopy.drugUsageRecord = drugUsageRecordEntriesCopy

                personalHealthLogViewModel.putDrugUsageRecordByID(heathLogID, drugUsageRecordCopy)

                Toast.makeText(this, "¯Added successfully!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DrugUsageActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DrugEffectivenessActivity::class.java)
        intent.putExtra("index", position)
        startActivity(intent)
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