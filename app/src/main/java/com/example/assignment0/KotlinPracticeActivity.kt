package com.example.assignment0

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.StringBuilder

class KotlinPracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_practice)

        findViewById<Button>(R.id.run_button).setOnClickListener{
            enterNumbers(it)
        }
    }

    private fun enterNumbers(view : View) {
        val input = findViewById<EditText>(R.id.edit_text)
        val splitted = input.text.split(" ")

        val nums = arrayListOf<Int>()

        val a = StringBuilder()
        val b = StringBuilder()
        val c = StringBuilder()


        for(i in 0..splitted.size-1){
            a.append(splitted[i]+"명 ")
            if(splitted[i].toInt()%2==0) {
                b.append(splitted[i]+" ")
            }
            nums.add(splitted[i].toInt())
        }

        //naive bubble sort
        for(i in 0..nums.size-1) {
            for(j in 0..nums.size-2) {
                if(nums[j]>nums[j+1]) {
                    val temp = nums[j]
                    nums[j] = nums[j+1]
                    nums[j+1] = temp
                }
            }
        }

        for(i in 0..nums.size-1) {
            c.append(nums[i].toString()+" ")
        }

        val total = StringBuilder()
        total.append(a.toString()+"\n"+b.toString()+"\n"+c.toString())
        findViewById<TextView>(R.id.resultText).text = total

        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)


    }

}