package com.example.healthcope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.model.Reminders
import java.text.SimpleDateFormat
import java.util.Date

class reminderAdapter: ListAdapter<Reminders, reminderAdapter.reminderViewHolder>(
    reminderAdapter.Reminder_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): reminderViewHolder {
        return reminderViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: reminderViewHolder, position: Int) {
        val current = getItem(position)
        var sdf = SimpleDateFormat("HH:mm:ss")
//        var time = sdf.format(current.time)
        holder.bind(current.drugs)
    }

    class reminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): reminderViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.emergency_contact_view_item, parent, false)
                return reminderViewHolder(view)
            }
        }
    }

    companion object {
        private val Reminder_COMPARATOR = object : DiffUtil.ItemCallback<Reminders>() {
            override fun areItemsTheSame(oldItem: Reminders, newItem: Reminders): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Reminders, newItem: Reminders): Boolean {
                return oldItem.drugs == newItem.drugs &&
                        oldItem.id == newItem.id
//                       && oldItem.time == newItem.time
            }
        }
    }
}