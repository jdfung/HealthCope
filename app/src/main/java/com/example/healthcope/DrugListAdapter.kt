package com.example.healthcope

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DrugListAdapter(private val context : Activity, private val drugList : ArrayList<Drug>) :ArrayAdapter<Drug>(context, R.layout.drug_list_item, drugList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.drug_list_item, null)

        val drugNameView : TextView = view.findViewById(R.id.drugName)
        //val ratingView : TextView = view.findViewById(R.id.drugRating)

        drugNameView.text = drugList[position].drugName
        //ratingView.text = drugList[position].ratingVal as String

        return view
    }

}