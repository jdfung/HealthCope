package com.example.healthcope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_drug_usage.*
import kotlinx.android.synthetic.main.activity_set_interests.*

class SetInterestsActivity : AppCompatActivity() {

    private lateinit var topicsArrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_interests)

        val actionBar = supportActionBar

        actionBar!!.title = "Set Interests"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val topicName = arrayOf(
            "TOPIC_1",
            "TOPIC_2",
            "TOPIC_3",
            "TOPIC_4",
            "TOPIC_5",
            "TOPIC_6",
            "TOPIC_7",
            "TOPIC_8",
            "TOPIC_9",
            "TOPIC_10",
            "TOPIC_11",
            "TOPIC_12",
            "TOPIC_13"
        )

        topicsArrayList = ArrayList()

        for(i in topicName.indices){
            topicsArrayList.add(topicName[i])
        }

        val topicListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, topicsArrayList)
        interestsListView.adapter = topicListAdapter

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