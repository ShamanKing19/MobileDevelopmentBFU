package com.sukhomlin.firstAndroidApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val login = storage.getString("login", "")
        val password = storage.getString("password", "")
        val isAutoLogin = storage.getBoolean("isAutologin", false)

        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val handler = Handler()
        val stepsCount = 1
        var currentStep = 0
        var progressStatus = 0
        val stepSize = 100 / stepsCount


        Thread {
            while (currentStep < stepsCount) {
                Thread.sleep(1000)
                progressStatus += stepSize
                currentStep++
                handler.post {
                    progressBar.progress = progressStatus
                }
            }

            val intent: Intent
            if ((login != "") and (password != "") and isAutoLogin) {
                intent = Intent(this, ContentActivity::class.java)
            } else if ((login != "") and (password !== "") and !isAutoLogin) {
                intent = Intent(this, LoginActivity::class.java)
            } else {
                intent = Intent(this, MainActivity::class.java)
            }
            startActivity(intent)

        }.start()
    }
}