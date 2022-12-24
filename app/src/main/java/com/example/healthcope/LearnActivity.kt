package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_learn.*
import kotlinx.android.synthetic.main.activity_manage_personal_health.*

class LearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        setSupportActionBar(learnToolbar)

        discoverArticlesBtn.setOnClickListener{
            val intent = Intent(this, LearnArticlesTopicActivity::class.java)
            startActivity(intent)
        }

        discoverVideosBtn.setOnClickListener{
            val intent = Intent(this, LearnVideosTopicActivity::class.java)
            startActivity(intent)
        }

        learnHomeNavOpt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        learnManageNavOpt.setOnClickListener{
            val intent = Intent(this, ManagePersonalHealthActivity::class.java)
            startActivity(intent)
        }

        learnProfileNavOpt.setOnClickListener{
            val intent = Intent(this, User_Profile::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_learn, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.interest_setting) {
            val intent = Intent(this, SetInterestsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}