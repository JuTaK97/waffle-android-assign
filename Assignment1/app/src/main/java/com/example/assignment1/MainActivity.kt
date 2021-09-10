package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.assignment1.databinding.ActivityMainBinding
import timber.log.Timber
import java.util.Observer

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.setOnClickListener {viewModel.click(0)}
        binding.button2.setOnClickListener {viewModel.click(1)}
        binding.button3.setOnClickListener {viewModel.click(2)}
        binding.button4.setOnClickListener {viewModel.click(3)}
        binding.button5.setOnClickListener {viewModel.click(4)}
        binding.button6.setOnClickListener {viewModel.click(5)}
        binding.button7.setOnClickListener {viewModel.click(6)}
        binding.button8.setOnClickListener {viewModel.click(7)}
        binding.button9.setOnClickListener {viewModel.click(8)}
        binding.buttonRestart.setOnClickListener{viewModel.restart()}

        val buttonList = listOf(binding.button1,binding.button2,binding.button3,
                                binding.button4,binding.button5,binding.button6,
                                binding.button7,binding.button8,binding.button9)

        viewModel.data.observe(this, {
            for(i in 0 until 9) buttonList[i].text = it[i].toString()
        })
        viewModel.status.observe(this, {
            binding.status.text = it
        })


    }
}