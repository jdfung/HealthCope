package com.example.healthcope

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SimpleListAdapter(private val context : Activity, private val topicList : ArrayList<String>) : ArrayAdapter<String>(context, R.layout.simple_list_item, topicList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.simple_list_item, null)

        val topicTitleView : TextView = view.findViewById(R.id.topicTitle)
        //val ratingView : TextView = view.findViewById(R.id.drugRating)

        topicTitleView.text = topicList[position]
        //ratingView.text = drugList[position].ratingVal as String

        return view
    }

}