package com.wafflestudio.assignment3.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.wafflestudio.assignment3.databinding.ActivityDetailBinding



class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var lectureAdapter: LectureAdapter
    private lateinit var lectureLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lectureAdapter = LectureAdapter()
        lectureLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewLecture.apply {
            adapter = lectureAdapter
            layoutManager = lectureLayoutManager
        }

        val id : Int = intent.getIntExtra("id", 0)
        viewModel.fetchMember(id).observe(this) {
            Glide.with(this).load(it.profileImage).into(binding.imageViewProfile)
            binding.textMemberName.text = it.name
            lectureAdapter.setLectures(it.lectures)
        }
    }
}
