package com.example.healthcope

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_health_log.*

class HealthLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_log)

        val actionBar = supportActionBar

        actionBar!!.title = "Health Log"

        actionBar.setDisplayHomeAsUpEnabled(true)

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
        val id: Int = item.getItemId()
        if (id == android.R.id.home) {
            onBackPressed()
            return true
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