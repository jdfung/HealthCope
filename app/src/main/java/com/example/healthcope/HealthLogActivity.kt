package com.example.healthcope

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcope.application.SingletonApplication
import kotlinx.android.synthetic.main.activity_health_log.*

class HealthLogActivity() : AppCompatActivity() {

    private val personalHealthLogViewModel: PersonalHealthLogViewModel by viewModels {
        PersonalHealthLogViewModelFactory((application as SingletonApplication).repositoryPersonalHealthLog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_log)

        val actionBar = supportActionBar

        actionBar!!.title = "Health Log"

        actionBar.setDisplayHomeAsUpEnabled(true)

//        val healthLogID : Int = intent.getStringExtra("healthLogID")!!.toInt()
//        println("DEBUG 2: $healthLogID")
//        personalHealthLogViewModel.loadHealthLogByID(healthLogID)

        val dateTextView : TextView = findViewById<EditText>(R.id.dateTextView)

        personalHealthLogViewModel.healthLogByID.observe(this){
                healthLog ->
            dateTextView.text = healthLog.date
        }

        openMentalConditionActivityOpt.setOnClickListener{
            val intent = Intent(this, MentalConditionActivity::class.java)
            startActivity(intent)
        }

        openPhysicalConditionActivityOpt.setOnClickListener{
            val intent = Intent(this, PhysicalConditionActivity::class.java)
            startActivity(intent)
        }

        openDrugUsageActivityOpt.setOnClickListener{
            val intent = Intent(this, DrugUsageActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_health_log, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
            finish()
        }
        if (id == R.id.share) {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.popup_share,null)

            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Share")

            val mAlertDialog=mBuilder.show()
        }
        return super.onOptionsItemSelected(item)
    }
}