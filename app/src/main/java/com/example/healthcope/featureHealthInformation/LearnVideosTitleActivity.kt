package com.example.healthcope.featureHealthInformation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import com.example.healthcope.R
import com.example.healthcope.featureTrackAndCheckIn.SimpleListAdapter

class LearnVideosTitleActivity : AppCompatActivity() {
    private lateinit var titlesArrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_videos_title)

        val actionBar = supportActionBar

        actionBar!!.title = "Videos"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val topicName = arrayOf(
            "TITLE_1",
            "TITLE_2",
            "TITLE_3",
            "TITLE_4",
            "TITLE_5",
            "TITLE_6",
            "TITLE_7",
            "TITLE_8",
            "TITLE_9",
            "TITLE_10",
            "TITLE_11",
            "TITLE_12",
            "TITLE_13"
        )

        titlesArrayList = ArrayList()

        for(i in topicName.indices){
            titlesArrayList.add(topicName[i])
        }

        val arrayAdapter = SimpleListAdapter(this, titlesArrayList)
        val videoTitlesListView : ListView = findViewById<ListView>(R.id.videoTitlesListView)

        videoTitlesListView.adapter = arrayAdapter

        videoTitlesListView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(this, LearnVideosActivity::class.java)
            startActivity(intent)
        }
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