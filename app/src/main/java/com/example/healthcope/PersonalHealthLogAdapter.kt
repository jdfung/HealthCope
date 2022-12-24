package com.example.healthcope

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PersonalHealthLogAdapter(private val context : Activity, private val drugList : ArrayList<HealthLog>) :ArrayAdapter<HealthLog>(context, R.layout.drug_list_item, drugList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.health_log_list_item, null)

        val healthLogTitleView : TextView = view.findViewById(R.id.healthLogTitle)
        //val ratingView : TextView = view.findViewById(R.id.drugRating)

        healthLogTitleView.text = drugList[position].healthLogTitle
        //ratingView.text = drugList[position].ratingVal as String

        return view
    }

}