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

        binding.buttonCreate.setOnClickListener {
            try {
                val param = CreateSeminarRequest(
                    binding.buttonOnline.isChecked,
                    binding.textTime.text.toString(),
                    binding.textSeminarName.text.toString(),
                    binding.textCapacity.text.toString().toInt(),
                    binding.textCount.text.toString().toInt())

                val response =  viewModel.createSeminar(param)
                response.enqueue(object : Callback<CreateSeminarFetch> {
                    override fun onFailure(call: Call<CreateSeminarFetch>, t: Throwable) {
                        Toast.makeText(this@CreateSeminarActivity, "Failed to create", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(
                        call: Call<CreateSeminarFetch>,
                        response: Response<CreateSeminarFetch>
                    ) {
                        if(response.isSuccessful) {
                            Timber.d("DetailActivity start")
                            val intent = Intent(this@CreateSeminarActivity, DetailSeminarActivity::class.java)
                            intent.putExtra("id", response.body()!!.id)
                            intent.putExtra("role", getIntent().getStringExtra("role").toString())
                            startActivity(intent)
                            finish()
                        }
                        else {
                            Toast.makeText(this@CreateSeminarActivity,
                                response.errorBody().toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                })

            } catch (e:Exception) {
                Toast.makeText(this, e.toString(),Toast.LENGTH_LONG).show()
            }

        }
    }


}