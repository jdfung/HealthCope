package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_drug_usage.*
import kotlinx.android.synthetic.main.activity_personal_health_log.*

class PersonalHealthLogActivity : AppCompatActivity() {

    private lateinit var personalHealthLog: ArrayList<HealthLog>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_health_log)

        val actionBar = supportActionBar

        actionBar!!.title = "Personal Health Log"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val healthLogTitle = arrayOf("Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY",
            "Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY","Health log at DD/MM/YY")

        personalHealthLog = ArrayList()

        for(i in healthLogTitle.indices){
            val title = HealthLog(healthLogTitle[i])
            personalHealthLog.add(title)
        }

        val arrayAdapter = PersonalHealthLogAdapter(this, personalHealthLog)
        personalHealthLogListView.adapter = arrayAdapter

        personalHealthLogListView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(this, HealthLogActivity::class.java)
            startActivity(intent)
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