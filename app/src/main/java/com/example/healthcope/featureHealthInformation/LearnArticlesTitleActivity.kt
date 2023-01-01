package com.example.healthcope.featureHealthInformation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.R
import com.example.healthcope.application.HealthCopeApplication
import com.example.healthcope.model.NewsArticle
import com.example.healthcope.viewModel.NewsViewModel
import com.example.healthcope.viewModel.NewsViewModelFactory

class LearnArticlesTitleActivity : AppCompatActivity(), NewsListAdapter.NewsListOnItemClickListener, OnItemSelectedListener {

//    private val newsViewModel : NewsViewModel by viewModels {
//        NewsViewModelFactory((application as NewsApplication).repositoryNews)
//    }
    private val newsViewModel : NewsViewModel by viewModels<NewsViewModel> {
        NewsViewModelFactory((application as HealthCopeApplication).repositoryNews)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_articles_title)

        val actionBar = supportActionBar

        actionBar!!.title = "Articles"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val setInterestSpinner : Spinner = findViewById<Spinner>(R.id.setInterestSpinner)
        val simpleAdapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,
            R.array.interestsList, android.R.layout.simple_spinner_item)

        simpleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        setInterestSpinner.adapter = simpleAdapter
        setInterestSpinner.onItemSelectedListener = this

        val rv = findViewById<RecyclerView>(R.id.articleTitlesListView)
        val progressBar = findViewById<ProgressBar>(R.id.articleTitleListProgressBar)

        var newsArticlesCopy : List<NewsArticle>?

        newsViewModel.newsArticles.observe(this){
            newsResponse ->
            newsArticlesCopy = newsResponse.articles

            rv.layoutManager = LinearLayoutManager(this)
            val adapter = NewsListAdapter(this)
            rv.adapter = adapter
            adapter.submitList(newsArticlesCopy)
            progressBar.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long){
        val interest : String = parent!!.getItemAtPosition(position).toString()

        println("DEBUG: $interest")
        newsViewModel.queryNews(interest)
    }

    override fun onNothingSelected(parent: AdapterView<*>?){

    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LearnArticlesActivity::class.java)
        intent.putExtra("index", position)
        startActivity(intent)
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