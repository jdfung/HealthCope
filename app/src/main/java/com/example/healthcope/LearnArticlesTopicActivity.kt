package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_learn_articles_topic.*

class LearnArticlesTopicActivity : AppCompatActivity() {

    private lateinit var topicsArrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_articles_topic)

        val actionBar = supportActionBar

        actionBar!!.title = "Article Topics"

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

        val arrayAdapter = SimpleListAdapter(this, topicsArrayList)
        articleTopicsListView.adapter = arrayAdapter

        articleTopicsListView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(this, LearnArticlesTitleActivity::class.java)
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