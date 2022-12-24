package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.activity_manage_personal_health.*

class ManagePersonalHealthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_personal_health)


        openHealthLogActivityBtn.setOnClickListener{
            val intent = Intent(this, HealthLogActivity::class.java)
            startActivity(intent)
        }


        openPersonalHealthLogActivityBtn.setOnClickListener{
            val intent = Intent(this, PersonalHealthLogActivity::class.java)
            startActivity(intent)
        }

        manageHomeNavOpt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        manageLearnNavOpt.setOnClickListener{
            val intent = Intent(this, LearnActivity::class.java)
            startActivity(intent)
        }

        manageProfileNavOpt.setOnClickListener{
            val intent = Intent(this, User_Profile::class.java)
            startActivity(intent)
        }
    }
}