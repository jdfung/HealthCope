package com.example.healthcope

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class add_emergency_contacts : AppCompatActivity() {

    lateinit var addContact: ImageButton
    lateinit var list: ListView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emergency_contacts)

        val actionBar = supportActionBar
        actionBar!!.title = "Emergency Contacts"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val arrayAdapter: ArrayAdapter<*>
        val myArrayList = ArrayList<String>()

        myArrayList.add("012-3456789")
        myArrayList.add("012-7567463")

        var mListView = findViewById<ListView>(R.id.list)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myArrayList)
        mListView.adapter = arrayAdapter

        addContact = findViewById<ImageButton>(R.id.btn_add_contact)

        openDialog(myArrayList, arrayAdapter)

    }


    private fun openDialog(arraylist: ArrayList<String>, arrayAdapter: ArrayAdapter<*>){
        addContact.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.MyDialogTheme)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)


            with(builder){
                setTitle("Add Emergency contact")
                setPositiveButton("Save"){dialog, which ->

                    if(editText.text.toString().isEmpty())
                    {
                        editText.setError("Please enter a number")
                    }
                    else {
                        arraylist.add(editText.text.toString())
                        editText.setText("");
                        arrayAdapter.notifyDataSetChanged()
                    }
                }
                setNegativeButton("Cancel"){dialog, which -> Log.d("Main", "Negative button clicked")}
                setView(dialogLayout)
                show()
            }

        }
    }
}