package com.example.healthcope.featureTrackAndCheckIn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.R
import com.example.healthcope.model.DrugUsageRecordEntry

class DrugListAdapter(private val listener: DrugListOnItemClickListener)
    : ListAdapter<DrugUsageRecordEntry, DrugListAdapter.DrugListViewHolder>(ENTRY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugListViewHolder {
        return DrugListViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: DrugListViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class DrugListViewHolder(itemView: View, private val listener: DrugListOnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var drugUsageRecordEntry: DrugUsageRecordEntry = DrugUsageRecordEntry()
        private val textItemView: TextView = itemView.findViewById(R.id.drugName)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(entry: DrugUsageRecordEntry?) {
            drugUsageRecordEntry = entry!!
            val print = drugUsageRecordEntry.drugName
            textItemView.text = print
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                listener: DrugListOnItemClickListener
            ): DrugListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.drug_list_item, parent, false)
                return DrugListViewHolder(view, listener)
            }
        }
    }

    interface DrugListOnItemClickListener{
        fun onItemClick(position: Int)
    }

    companion object {
        private val ENTRY_COMPARATOR = object : DiffUtil.ItemCallback<DrugUsageRecordEntry>() {
            override fun areItemsTheSame(oldItem: DrugUsageRecordEntry, newItem: DrugUsageRecordEntry): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DrugUsageRecordEntry, newItem: DrugUsageRecordEntry): Boolean {
                return oldItem.drugName == newItem.drugName &&
                        oldItem.rating == newItem.rating &&
                        oldItem.note == newItem.note
            }
        }
    }
}