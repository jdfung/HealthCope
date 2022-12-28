package com.example.healthcope.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthcope.model.NewsResponse
import com.example.healthcope.network.API_KEY
import com.example.healthcope.repository.NewsRepository

class NewsViewModel(private val repository: NewsRepository): ViewModel() {
    val newsArticles : LiveData<NewsResponse> = repository.readNews()

    fun queryNews(q: String?){
        repository.getNews(q, API_KEY)
    }
}

class NewsViewModelFactory(private val repository: NewsRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}