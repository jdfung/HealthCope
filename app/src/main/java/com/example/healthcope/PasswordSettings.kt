package com.example.healthcope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.healthcope.model.EmergencyContact
import com.example.healthcope.model.userAccount
import com.example.healthcope.model.userHealthStatus
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordSettings : AppCompatActivity() {
    lateinit var reset_pw: Button
    lateinit var email: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_settings)

        val actionBar = supportActionBar
        actionBar!!.title = "Password Settings"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        reset_pw = findViewById((R.id.continue_btn))
        email = findViewById(R.id.editTextTextEmailAddress)

        reset_pw.setOnClickListener {
//            val Intent = Intent(this, ResetPassword::class.java)
//            startActivity(Intent)
            checkEmail()
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun checkEmail() {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userAccount>> = api?.getAllAccounts()!!
        call.enqueue(object: Callback<List<userAccount>> {
            override fun onResponse(call: Call<List<userAccount>>, response: Response<List<userAccount>>) {
                if(response.isSuccessful) {
                    val filterStats: List<userAccount>? = response.body()?.filter {
                        it.email == email.text.toString()}
                    if(filterStats!!.isEmpty()) {
                        Toast.makeText(this@PasswordSettings, "No such email", Toast.LENGTH_LONG).show()
                    }
                    else{
                        val Intent = Intent(this@PasswordSettings, ResetPassword::class.java)
                        Intent.putExtra("emailAddress", email.text.toString())
                        startActivity(Intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<userAccount>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

    }

}