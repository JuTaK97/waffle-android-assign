package com.example.assignment0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.introduce_Button).setOnClickListener{
            val intent = Intent(this, IntroduceActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.KotlinPracticeActivityButton).setOnClickListener{
            val intent2 = Intent(this, KotlinPracticeActivity::class.java)
            startActivity(intent2)
        }
    }





}