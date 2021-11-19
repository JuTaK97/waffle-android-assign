package com.example.assignment4.ui.seminar.create

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment4.databinding.ActivityCreateSeminarBinding
import com.example.assignment4.ui.seminar.detail.DetailSeminarActivity
import com.example.assignment4.dto.CreateSeminarFetch
import com.example.assignment4.dto.CreateSeminarRequest
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception


@AndroidEntryPoint
class CreateSeminarActivity: AppCompatActivity() {
    private lateinit var binding : ActivityCreateSeminarBinding
    private val viewModel : CreateSeminarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateSeminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myRole = getIntent().getStringExtra("role").toString()

        binding.buttonCreate.setOnClickListener {
            val param = CreateSeminarRequest(
                binding.buttonOnline.isChecked,
                binding.textTime.text.toString(),
                binding.textSeminarName.text.toString(),
                binding.textCapacity.text.toString().toIntOrNull(),
                binding.textCount.text.toString().toIntOrNull())
            viewModel.createSeminar(param)
        }
        viewModel.result.observe(this, {
            if(it=="success") {
                val intent = Intent(this@CreateSeminarActivity, DetailSeminarActivity::class.java)
                intent.putExtra("id", viewModel.id.value!!.toInt())
                intent.putExtra("role", myRole)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }


}