package com.wafflestudio.assignment3.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.wafflestudio.assignment3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var memberAdapter: MemberAdapter
    private lateinit var memberLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memberAdapter = MemberAdapter()
        memberLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewMember.apply {
            adapter = memberAdapter
            layoutManager = memberLayoutManager
        }

        viewModel.fetchMemberList()
        viewModel.observeMember().observe(this) {
            memberAdapter.setMembers(it)
        }
    }
}
