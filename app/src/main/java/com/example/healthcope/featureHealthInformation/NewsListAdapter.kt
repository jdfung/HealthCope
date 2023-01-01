package com.example.healthcope.featureHealthInformation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.R
import com.example.healthcope.model.NewsArticle

class NewsListAdapter(private val listener : NewsListOnItemClickListener)
    : ListAdapter<NewsArticle, NewsListAdapter.NewsViewHolder>(ENTRY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class NewsViewHolder(itemView: View, private val listener: NewsListOnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var newsArticle : NewsArticle = NewsArticle()
        private val textItemView: TextView = itemView.findViewById(R.id.topicTitle)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(entry: NewsArticle?) {
            newsArticle = entry!!
            textItemView.text = newsArticle.title
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        companion object {
            fun create(parent: ViewGroup, listener: NewsListOnItemClickListener): NewsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_article_list_item, parent, false)
                return NewsViewHolder(view, listener)
            }
        }
    }

    interface NewsListOnItemClickListener{
        fun onItemClick(position: Int)
    }

    companion object {
        private val ENTRY_COMPARATOR = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
                return oldItem.source == newItem.source &&
                        oldItem.author == newItem.author &&
                        oldItem.title == newItem.title &&
                        oldItem.description == newItem.description &&
                        oldItem.url == newItem.url &&
                        oldItem.urlToImage == newItem.urlToImage &&
                        oldItem.publishedAt == newItem.publishedAt &&
                        oldItem.content == newItem.content
            }
        }
    }

}