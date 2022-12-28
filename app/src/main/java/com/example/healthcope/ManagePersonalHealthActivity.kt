package com.example.healthcope

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.example.healthcope.application.SingletonApplication
import com.example.healthcope.model.DrugUsageRecord
import com.example.healthcope.model.HealthLog
import com.example.healthcope.model.MentalConditionRecordEntry
import com.example.healthcope.model.PhysicalConditionRecordEntry
import java.text.SimpleDateFormat
import java.util.*

class ManagePersonalHealthActivity : AppCompatActivity() {

    private val personalHealthLogViewModel: PersonalHealthLogViewModel by viewModels {
        PersonalHealthLogViewModelFactory((application as SingletonApplication).repositoryPersonalHealthLog)
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_personal_health)

        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val current = formatter.format(date)
        val today : String = current.toString()

        lateinit var personalHealthLogCopy : List<HealthLog>

        personalHealthLogViewModel.personalHealthLog.observe(this){
            personalHealthLog ->
            personalHealthLogCopy = personalHealthLog
        }

        val addNewHealthLogButton : Button = findViewById<Button>(R.id.openHealthLogActivityBtn)
        val addPersonalHealthLogButton : Button = findViewById<Button>(R.id.openPersonalHealthLogActivityBtn)
        val manageHomeNavOption : TextView = findViewById<TextView>(R.id.manageHomeNavOpt)
        val manageLearnNavOption : TextView = findViewById<TextView>(R.id.manageLearnNavOpt)
        val manageProfileNavOption : TextView = findViewById<TextView>(R.id.manageProfileNavOpt)

        addNewHealthLogButton.setOnClickListener{
            val latestHealthLog : HealthLog? = personalHealthLogCopy.lastOrNull()

            if(latestHealthLog == null){
                personalHealthLogViewModel.postHealthLog(HealthLog(0, today))
                personalHealthLogViewModel.postMentalConditionRecordEntry(MentalConditionRecordEntry())
                personalHealthLogViewModel.postPhysicalConditionRecordEntry(PhysicalConditionRecordEntry())
                personalHealthLogViewModel.postDrugUsageRecord(DrugUsageRecord())
            }
            else{
                if(latestHealthLog.date != today){
                    personalHealthLogViewModel.postHealthLog(HealthLog(0, today))
                    personalHealthLogViewModel.postMentalConditionRecordEntry(MentalConditionRecordEntry())
                    personalHealthLogViewModel.postPhysicalConditionRecordEntry(PhysicalConditionRecordEntry())
                    personalHealthLogViewModel.postDrugUsageRecord(DrugUsageRecord())
                }
                else{
                    val latestHealthLogID : Int = latestHealthLog.healthLogID
                    personalHealthLogViewModel.loadHealthLogByID(latestHealthLogID)
                    personalHealthLogViewModel.loadMentalConditionRecordEntryByID(latestHealthLogID)
                    personalHealthLogViewModel.loadPhysicalConditionRecordEntryByID(latestHealthLogID)
                    personalHealthLogViewModel.loadDrugUsageRecordByID(latestHealthLogID)
                }
            }

            val intent = Intent(this, HealthLogActivity::class.java)
            startActivity(intent)
        }

        addPersonalHealthLogButton.setOnClickListener{
            val intent = Intent(this, PersonalHealthLogActivity::class.java)
            startActivity(intent)
        }

        manageHomeNavOption.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        manageLearnNavOption.setOnClickListener{
            val intent = Intent(this, LearnActivity::class.java)
            startActivity(intent)
        }

        manageProfileNavOption.setOnClickListener{
            val intent = Intent(this, User_Profile::class.java)
            startActivity(intent)
        }
    }
}