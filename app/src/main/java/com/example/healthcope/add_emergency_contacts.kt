package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcope.model.EmergencyContact
import com.example.mvvmroomgroup2.emergencyContactAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class add_emergency_contacts : AppCompatActivity() {

    private val contactViewModel: EmergencyContViewModel by viewModels{
        ContactViewModelFactory((application as HealthCopeApplication).repository)
    }

    lateinit var addContact: ImageButton
    lateinit var list: ListView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emergency_contacts)

        val actionBar = supportActionBar
        actionBar!!.title = "Emergency Contacts"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //val arrayAdapter: ArrayAdapter<*>
        //val myArrayList = ArrayList<String>()

        //myArrayList.add("012-3456789")
        //myArrayList.add("012-7567463")

        //var mListView = findViewById<ListView>(R.id.list)
        //arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myArrayList)
        //mListView.adapter = arrayAdapter

        val recyclerview = findViewById<RecyclerView>(R.id.list)

        contactViewModel.allContacts.observe(this) {
            contact ->
            recyclerview.layoutManager = LinearLayoutManager(this)
            val adapter = emergencyContactAdapter()
            recyclerview.adapter = adapter
            adapter.submitList(contact)
            //progressBar.visibility = View.GONE
            //recyclerview.visibility = View.VISIBLE
//            adapter.notifyDataSetChanged()
        }

//        recyclerview.layoutManager = LinearLayoutManager(this)
//        val data = ArrayList<EmergencyContViewModel>()
//        val adapter = emergencyContactAdapter(data)
//        recyclerview.adapter = adapter

        addContact = findViewById<ImageButton>(R.id.btn_add_contact)
        openDialog()

    }


    private fun openDialog(/*arraylist: ArrayList<EmergencyContViewModel>, arrayAdapter: emergencyContactAdapter*/){
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
                        //arraylist.add(editText.text.toString())
                        val contact = editText.text.toString()
                        val newContact = EmergencyContact(0,contact)
                        lifecycleScope.launch(Dispatchers.IO) {
                            contactViewModel.addContact(newContact)
                        }
                        val intent = Intent(this@add_emergency_contacts, add_emergency_contacts::class.java)
                        startActivity(intent)
                        finish()
                        editText.setText("");
                        //arrayAdapter.notifyDataSetChanged()
                    }
                }
                setNegativeButton("Cancel"){dialog, which -> Log.d("Main", "Negative button clicked")}
                setView(dialogLayout)
                show()
            }

        }
    }
}