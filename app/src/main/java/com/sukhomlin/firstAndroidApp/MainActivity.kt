package com.sukhomlin.firstAndroidApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    var currentLoginType = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        this.initTabSwitching()
        this.initRegisterButton()
    }


    @SuppressLint("ShowToast")
    fun initRegisterButton() {
        val loginInput = findViewById<EditText>(R.id.loginInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val repeatPasswordInput = findViewById<EditText>(R.id.repeatPasswordInput)

        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val login = loginInput.text.toString()
            val password = passwordInput.text.toString()
            val repeatedPassword = repeatPasswordInput.text.toString()

            val validateChar = if(this.currentLoginType == "email") '@' else '+'
            val isValidLogin = login.contains(validateChar)
            val isValidPassword = (password.length > 7) and (password == repeatedPassword)

            if(!(isValidLogin and isValidPassword)) {
                val toast = Toast.makeText(this@MainActivity, "Ошибка", Toast.LENGTH_LONG)
                toast.show()
            } else {
                // TODO: Следующая лаба
            }
        }

    }


    fun initTabSwitching() {
        val emailLoginTab = findViewById<TextView>(R.id.emailTab)
        val phoneLoginTab = findViewById<TextView>(R.id.phoneTab)
        val loginInput = findViewById<EditText>(R.id.loginInput)

        emailLoginTab.setOnClickListener{
            phoneLoginTab.setTextColor(resources.getColor(R.color.black))
            emailLoginTab.setTextColor(resources.getColor(R.color.purple_500))
            loginInput.hint = "Введите Email"
            loginInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
            this.currentLoginType = "email"
        }

        phoneLoginTab.setOnClickListener{
            emailLoginTab.setTextColor(resources.getColor(R.color.black))
            phoneLoginTab.setTextColor(resources.getColor(R.color.purple_500))
            loginInput.hint = "Введите телефон"
            loginInput.setInputType(InputType.TYPE_CLASS_PHONE)
            this.currentLoginType = "phone"
        }
    }
}