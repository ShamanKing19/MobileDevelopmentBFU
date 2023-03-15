package com.sukhomlin.firstAndroidApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.initLoginButton()
    }

    fun initLoginButton() {
        val loginInput = findViewById<EditText>(R.id.loginInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val autologinCheckbox = findViewById<CheckBox>(R.id.autologinCheckbox)
        val loginButton = findViewById<Button>(R.id.loginButton)

        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val savedLogin = storage.getString("login", "")
        val savedPassword = storage.getString("password", "")

        loginButton.setOnClickListener{
            val login = loginInput.text.toString()
            val password = passwordInput.text.toString()
            val isAutologin = autologinCheckbox.isChecked()

            if((login == savedLogin) && (password == savedPassword)) {
                if(isAutologin){
                    storage.edit().putBoolean("isAutologin", true).apply()
                }
                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            } else {
                val toast = Toast.makeText(this@LoginActivity, "Ошибка", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}