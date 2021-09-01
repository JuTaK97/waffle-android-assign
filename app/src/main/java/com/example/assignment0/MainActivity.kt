package com.example.assignment0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val introButton: Button = findViewById(R.id.introduceButton)
        introButton.setOnClickListener{
            val introIntent = Intent(this, IntroduceActivity::class.java )
            startActivity(introIntent)
        }

    }
}