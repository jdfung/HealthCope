package com.example.healthcope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.model.HealthLog

class PersonalHealthLogAdapter(private val listener: HealthLogOnItemClickListener)
    : ListAdapter<HealthLog, PersonalHealthLogAdapter.HealthLogViewHolder>(ENTRY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthLogViewHolder {
        return HealthLogViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: HealthLogViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class HealthLogViewHolder(itemView: View, private val listener: HealthLogOnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var healthLog : HealthLog = HealthLog()
        private val textItemView: TextView = itemView.findViewById(R.id.healthLogTitle)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(entry: HealthLog?) {
            healthLog = entry!!
            val print = "Health log at " + healthLog.date
            textItemView.text = print
        }

        override fun onClick(v: View?) {
            listener.onItemClick(healthLog)
        }

        companion object {
            fun create(parent: ViewGroup, listener: HealthLogOnItemClickListener): HealthLogViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.health_log_list_item, parent, false)
                return HealthLogViewHolder(view, listener)
            }
        }
    }

    interface HealthLogOnItemClickListener{
        fun onItemClick(healthLog: HealthLog)
    }

    companion object {
        private val ENTRY_COMPARATOR = object : DiffUtil.ItemCallback<HealthLog>() {
            override fun areItemsTheSame(oldItem: HealthLog, newItem: HealthLog): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: HealthLog, newItem: HealthLog): Boolean {
                return oldItem.date == newItem.date &&
                        oldItem.healthLogID == newItem.healthLogID
            }
        }
    }
}