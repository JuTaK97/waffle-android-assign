package com.example.assignment4.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment4.R
import com.example.assignment4.databinding.ActivityMainBinding
import com.example.assignment4.ui.user.User
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager :ViewPager2 = binding.mainView
        val viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)
        viewPager.adapter = viewPagerFragmentAdapter
    }

}