package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_learn_articles_title.*
import kotlinx.android.synthetic.main.activity_learn_articles_topic.*

class LearnArticlesTitleActivity : AppCompatActivity() {

    private lateinit var titlesArrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_articles_title)

        val actionBar = supportActionBar

        actionBar!!.title = "Articles"

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
        articleTitlesListView.adapter = arrayAdapter

        articleTitlesListView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(this, LearnArticlesActivity::class.java)
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