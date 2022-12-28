package com.example.healthcope

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.MutableLiveData
import com.example.healthcope.DialogWithData.Companion.TAG
import com.example.healthcope.model.userAccount
import com.example.healthcope.network.Retrofit_API
import com.example.healthcope.network.Retrofit_client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    lateinit var register_direct: TextView
    lateinit var forgotpw_direct: TextView
    lateinit var login_btn: AppCompatButton

    lateinit var userNameInput: TextView
    lateinit var passwordInput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register_direct = findViewById<TextView>(R.id.textViewRegister)
        forgotpw_direct = findViewById<TextView>(R.id.forgotPassword)
        login_btn = findViewById<AppCompatButton>(R.id.btnlogin)


        register_direct.setOnClickListener {
            val Intent = Intent(this, Register::class.java)
            startActivity(Intent)
        }

        forgotpw_direct.setOnClickListener {
            val Intent = Intent(this, Forgot_password::class.java)
            startActivity(Intent)
        }

        login_btn.setOnClickListener {
            userNameInput = findViewById<TextView>(R.id.inputUsername)
            passwordInput = findViewById<TextView>(R.id.inputPassword)
            login()
//            val Intent = Intent(this, MainActivity::class.java)
//            startActivity(Intent)
        }
    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }

    private val _account: MutableLiveData<List<userAccount>> = MutableLiveData()

    fun login(): MutableLiveData<List<userAccount>> {
        var api: Retrofit_API? = null
        api = Retrofit_client.getInstance()?.getApi()
        val call: Call<List<userAccount>> = api?.getAllAccounts()!!
        call.enqueue(object: Callback<List<userAccount>> {
            override fun onResponse(call: Call<List<userAccount>>, response: Response<List<userAccount>>) {
                if(response.isSuccessful) {
                    _account.postValue(response.body())
                    val filterStats: List<userAccount>? = response.body()?.filter {
                        it.userName == userNameInput.text.toString() &&
                        it.password == passwordInput.text.toString()}
                    if(filterStats!!.isEmpty()) {
                        Toast.makeText(this@Login, "No such account", Toast.LENGTH_LONG).show()
                    }
                    else{
                        val Intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(Intent)
                        Log.d(TAG, "login success")
                    }
                }
            }

            override fun onFailure(call: Call<List<userAccount>>, t: Throwable) {
                Log.e("RETROFIT ERROR", t.message.toString())
            }

        })

        return _account
    }


}