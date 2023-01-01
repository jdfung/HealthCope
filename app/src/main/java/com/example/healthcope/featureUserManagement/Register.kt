package com.example.healthcope.featureUserManagement

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.healthcope.R
import com.example.healthcope.model.userAccount
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class Register : AppCompatActivity() {

    lateinit var register_btn: AppCompatButton
    lateinit var userNameInput: TextView
    lateinit var emailInput: TextView
    lateinit var passwordInput: TextView
    lateinit var confPasswordInput: TextView

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn = findViewById<AppCompatButton>(R.id.btnRegister)

        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        register_btn.setOnClickListener {
            userNameInput = findViewById<TextView>(R.id.inputUsername)
            emailInput = findViewById<TextView>(R.id.inputEmail)
            passwordInput = findViewById<TextView>(R.id.inputPassword)
            confPasswordInput = findViewById<TextView>(R.id.confirmPassword)

            if(passwordInput.text.toString() == confPasswordInput.text.toString())
            {
                val username = userNameInput.text.toString()
                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()
                val newAccount = userAccount(0, username, email, password)
                lifecycleScope.launch(Dispatchers.IO) {
                    register(newAccount)
                }

                basicAlert()
            }
            else{
                Toast.makeText(this, "Password mismatch", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun register(account: userAccount): LiveData<userAccount> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val data: MutableLiveData<userAccount> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<userAccount> = api?.register(account)!!
            if(response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.value = response.body()
                }
            }
        }
        return data
    }

    fun basicAlert(){

        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = {
                dialog: DialogInterface, which: Int ->
            val Intent = Intent(this, Login::class.java)
            startActivity(Intent)
        }

        with(builder)
        {
            setTitle("Registration status")
            setMessage("Account registered successfully")
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }


    }
}