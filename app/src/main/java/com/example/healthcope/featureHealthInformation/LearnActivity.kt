package com.example.healthcope.featureHealthInformation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.healthcope.featureTrackAndCheckIn.ManagePersonalHealthActivity
import com.example.healthcope.R
import com.example.healthcope.featureUserManagement.User_Profile
import com.example.healthcope.featureUserHealthProfile.MainActivity
import kotlinx.android.synthetic.main.activity_learn.*

class LearnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        //setSupportActionBar(learnToolbar)

        discoverArticlesBtn.setOnClickListener{
            val intent = Intent(this, LearnArticlesTitleActivity::class.java)
            startActivity(intent)
        }

        val discoverVideosBtn : Button = findViewById<Button>(R.id.discoverVideosBtn)

        discoverVideosBtn.setOnClickListener{
            val intent = Intent(this, LearnVideosTitleActivity::class.java)
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

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_learn, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id: Int = item.itemId
//        if (id == R.id.interest_setting) {
//            val intent = Intent(this, SetInterestsActivity::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }
}