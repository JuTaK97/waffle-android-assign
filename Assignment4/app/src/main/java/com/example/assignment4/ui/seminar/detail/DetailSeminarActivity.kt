package com.example.assignment4.ui.seminar.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment4.databinding.ActivityDetailSeminarBinding
import com.example.assignment4.dto.DetailSeminarFetch
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import okhttp3.ResponseBody

import retrofit2.Converter
import javax.inject.Inject


@AndroidEntryPoint
class DetailSeminarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailSeminarBinding
    private val viewModel : DetailSeminarViewModel by viewModels()

    private lateinit var participantAdapter : ParticipantListAdapter
    private lateinit var participantLayoutManager: LinearLayoutManager

    private lateinit var instructorAdapter : InstructorListAdapter
    private lateinit var instructorLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSeminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        participantAdapter = ParticipantListAdapter()
        participantLayoutManager = LinearLayoutManager(this)

        instructorAdapter = InstructorListAdapter()
        instructorLayoutManager = LinearLayoutManager(this)

        binding.participantList.apply {
            adapter = participantAdapter
            layoutManager = participantLayoutManager
        }
        binding.instructorList.apply {
            adapter = instructorAdapter
            layoutManager = instructorLayoutManager
        }

        val id = intent.getIntExtra("id",0)
        val response = viewModel.getDetailSeminar(id)
        response.clone().enqueue(object : Callback<DetailSeminarFetch> {
            override fun onFailure(call: Call<DetailSeminarFetch>, t: Throwable) {
                Timber.d("GET seminar detail failed")
            }
            override fun onResponse(
                call: Call<DetailSeminarFetch>,
                response: Response<DetailSeminarFetch>
            ) {
                if(response.isSuccessful && response.body()!=null) {
                    Timber.d("GET seminar detail success")
                    binding.seminarCapacity.text = response.body()!!.capacity.toString()
                    binding.seminarCount.text = response.body()!!.count.toString()
                    binding.seminarName.text = response.body()!!.name
                    binding.seminarOnline.text = response.body()!!.online.toString()
                    binding.seminarTime.text = response.body()!!.time
                }
                else {
                    // TODO: error message
                }
            }
        })
        viewModel.instructorList.observe(this, {
            instructorAdapter.setDetailInstructorList(it)
        })
        viewModel.participantList.observe(this, {
            participantAdapter.setDetailParticipantList(it)
        })
        binding.buttonJoin.setOnClickListener {
            val responseJoin = viewModel.joinSeminar(id, intent.getStringExtra("role").toString())
            responseJoin.clone().enqueue(object : Callback<DetailSeminarFetch> {
                override fun onFailure(call: Call<DetailSeminarFetch>, t: Throwable) {
                    Timber.d("Join seminar failed")
                    Toast.makeText(this@DetailSeminarActivity, "Join seminar failed",
                        Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<DetailSeminarFetch>,
                    response: Response<DetailSeminarFetch>
                ) {
                    if(response.isSuccessful) {
                        Timber.d("Join seminar success")
                        binding.seminarCount.text = response.body()!!.count.toString()
                    }
                    else {
                        Timber.d("Join seminar unsuccessful")
                        Toast.makeText(this@DetailSeminarActivity, response.errorBody().toString()
                            ,Toast.LENGTH_LONG).show()
                        Toast.makeText(this@DetailSeminarActivity, response.body().toString()
                            ,Toast.LENGTH_LONG).show()
                    }
                }
            }

            )
        }
    }
    
}