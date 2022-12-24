package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_drug_usage.*

class DrugUsageActivity : AppCompatActivity() {

    private lateinit var drugArrayList: ArrayList<Drug>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_usage)

        val actionBar = supportActionBar

        actionBar!!.title = "Drug Usage"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val drugName = arrayOf(
            "DRUG_1",
            "DRUG_2",
            "DRUG_3",
            "DRUG_4",
            "DRUG_5",
            "DRUG_6",
            "DRUG_7",
            "DRUG_8",
            "DRUG_9",
            "DRUG_10",
            "DRUG_11",
            "DRUG_12",
            "DRUG_13"
        )

        val rating = arrayOf( 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3)

        val description = arrayOf(
            "TEXT_1",
            "TEXT_2",
            "TEXT_3",
            "TEXT_4",
            "TEXT_5",
            "TEXT_6",
            "TEXT_7",
            "TEXT_8",
            "TEXT_9",
            "TEXT_10",
            "TEXT_11",
            "TEXT_12",
            "TEXT_13",
        )

        drugArrayList = ArrayList()

        for(i in drugName.indices){
            val drug = Drug(drugName[i], rating[i], description[i])
            drugArrayList.add(drug)
        }

        drugListView.isClickable = true

        val drugListAdapter = DrugListAdapter(this, drugArrayList)
        drugListView.adapter = drugListAdapter

        drugListView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(this, DrugEffectivenessActivity::class.java)
            startActivity(intent)
        }

        addNewDrugBtn.setOnClickListener{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.popup_add_new_drug,null)

            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Add new drug")

            val mAlertDialog=mBuilder.show()

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