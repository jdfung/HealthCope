package com.example.healthcope.featureHealthInformation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import com.example.healthcope.R
import com.example.healthcope.application.HealthCopeApplication
import com.example.healthcope.model.NewsArticle
import com.example.healthcope.viewModel.NewsViewModel
import com.example.healthcope.viewModel.NewsViewModelFactory

class LearnArticlesActivity : AppCompatActivity() {

    private val newsViewModel : NewsViewModel by viewModels<NewsViewModel> {
        NewsViewModelFactory((application as HealthCopeApplication).repositoryNews)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_articles)

        val actionBar = supportActionBar

        actionBar!!.title = "Article"

        actionBar.setDisplayHomeAsUpEnabled(true)


        var newsArticlesCopy : List<NewsArticle>?

        val index = intent.getIntExtra("index", 0)

        val articleTitleTextView : TextView = findViewById<TextView>(R.id.articleTitle)
        val articleContextTextView : TextView = findViewById<TextView>(R.id.articleContent)

        newsViewModel.newsArticles.observe(this){
            newsResponse ->
            newsArticlesCopy = newsResponse.articles

            articleTitleTextView.text = newsArticlesCopy!![index].title
            articleContextTextView.text = newsArticlesCopy!![index].content
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