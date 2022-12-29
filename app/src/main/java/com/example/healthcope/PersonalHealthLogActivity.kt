package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.application.HealthCopeApplication
import com.example.healthcope.model.HealthLog

class PersonalHealthLogActivity : AppCompatActivity(), PersonalHealthLogAdapter.HealthLogOnItemClickListener {

    private val personalHealthLogViewModel : PersonalHealthLogViewModel by viewModels {
        PersonalHealthLogViewModelFactory((application as HealthCopeApplication).repositoryPersonalHealthLog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_health_log)

        val actionBar = supportActionBar

        actionBar!!.title = "Personal Health Log"

        actionBar.setDisplayHomeAsUpEnabled(true)

        lateinit var personalHealthLogCopy : List<HealthLog>

        val rv = findViewById<RecyclerView>(R.id.personalHealthLogListView)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        personalHealthLogViewModel.personalHealthLog.observe(this) {
                personalHealthLog ->

            personalHealthLogCopy = personalHealthLog

            rv.layoutManager = LinearLayoutManager(this)
            val adapter = PersonalHealthLogAdapter(this)
            rv.adapter = adapter
            adapter.submitList(personalHealthLogCopy)
            progressBar.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(healthLog: HealthLog) {
//        val print : String = healthLog?.date ?: "yyyy-mm-dd"
//        Toast.makeText(this, "Item $print clicked", Toast.LENGTH_SHORT).show()
        val healthLogID : Int = healthLog.healthLogID

        personalHealthLogViewModel.loadHealthLogByID(healthLogID)
        personalHealthLogViewModel.loadMentalConditionRecordEntryByID(healthLogID)
        personalHealthLogViewModel.loadPhysicalConditionRecordEntryByID(healthLogID)
        personalHealthLogViewModel.loadDrugUsageRecordByID(healthLogID)

        val intent = Intent(this, HealthLogActivity::class.java)
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