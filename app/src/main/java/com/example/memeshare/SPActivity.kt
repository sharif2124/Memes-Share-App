package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spactivity)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@SPActivity,MainActivity::class.java)
            startActivity(intent)
        },4000)
    }
}