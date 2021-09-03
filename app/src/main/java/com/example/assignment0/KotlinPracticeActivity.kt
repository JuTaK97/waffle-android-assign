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
import android.widget.Toast
import java.lang.NumberFormatException
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
        val edit_text = findViewById<EditText>(R.id.edit_text)

        //no inputs
        if(edit_text.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.string_emptyInput), Toast.LENGTH_SHORT).show()
            return
        }

        val splitted = edit_text.text.split(" ")
        val nums = arrayListOf<Int>()
        val a = StringBuilder()
        val b = StringBuilder()
        val c = StringBuilder()


        for(i in splitted.indices){

            //exceptions
            try {
                splitted[i].toInt()
            } catch (e : NumberFormatException) {
                Toast.makeText(this, getString(R.string.string_errorInput), Toast.LENGTH_SHORT).show()
                return
            }

            //build result A
            a.append(splitted[i]+"명 ")

            //build result B
            if(splitted[i].toInt()%2==0) {
                b.append(splitted[i]+" ")
            }

            //prepare for result C
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

        //build result C
        for(i in 0..nums.size-1) {
            c.append(nums[i].toString()+" ")
        }

        //build total output
        val total = StringBuilder()
        total.append(a.toString()+"\n")
        total.append( (if(b.isEmpty()) "짝수 없음" else b.toString())+"\n")
        total.append(c.toString())
        findViewById<TextView>(R.id.result_text).text = total

        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}