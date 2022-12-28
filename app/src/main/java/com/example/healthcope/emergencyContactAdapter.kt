package com.example.mvvmroomgroup2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.R
import com.example.healthcope.model.EmergencyContact


class emergencyContactAdapter
    : ListAdapter<EmergencyContact, emergencyContactAdapter.emergencyContactViewHolder>(EmergencyContact_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): emergencyContactViewHolder {
        return emergencyContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: emergencyContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.contact)
    }

    class emergencyContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): emergencyContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.emergency_contact_view_item, parent, false)
                return emergencyContactViewHolder(view)
            }
        }
    }

    companion object {
        private val EmergencyContact_COMPARATOR = object : DiffUtil.ItemCallback<EmergencyContact>() {
            override fun areItemsTheSame(oldItem: EmergencyContact, newItem: EmergencyContact): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: EmergencyContact, newItem: EmergencyContact): Boolean {
                return oldItem.contact == newItem.contact &&
                        oldItem.id == newItem.id
            }
        }
    }
}