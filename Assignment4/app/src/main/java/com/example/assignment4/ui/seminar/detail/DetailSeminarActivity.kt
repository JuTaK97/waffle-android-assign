package com.example.assignment4.ui.seminar.detail

import android.annotation.SuppressLint
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
class DetailSeminarActivity : AppCompatActivity(){


    private lateinit var binding : ActivityDetailSeminarBinding
    private val viewModel : DetailSeminarViewModel by viewModels()

    private lateinit var participantAdapter : ParticipantListAdapter
    private lateinit var participantLayoutManager: LinearLayoutManager

    private lateinit var instructorAdapter : InstructorListAdapter
    private lateinit var instructorLayoutManager: LinearLayoutManager



    @SuppressLint("SetTextI18n")
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

        val myRole = intent.getStringExtra("role").toString()
        val id = intent.getIntExtra("id",0)
        viewModel.getDetailSeminar(id)

        viewModel.seminarInfo.observe(this, {
            binding.seminarCapacity.text = "Capacity: "+it.capacity.toString()
            binding.seminarCount.text = "Count: "+it.count.toString()
            binding.seminarName.text = it.name
            if(it.online) {
                binding.seminarOnline.text = "Online"
            }
            else {
                binding.seminarOnline.text = "Offline"
            }
            binding.seminarTime.text = it.time
            participantAdapter.setDetailParticipantList(it.participants)
            instructorAdapter.setDetailInstructorList(it.instructors)
        })
        binding.buttonJoin.setOnClickListener {
            viewModel.joinSeminar(id, myRole)
        }
        viewModel.joinResult.observe(this, {
            Toast.makeText(this, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
        })
    }
    
}