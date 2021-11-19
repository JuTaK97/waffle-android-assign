package com.example.assignment4.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment4.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UserFragment(role : String) : Fragment() {
    private lateinit var binding : FragmentUserBinding
    private val viewModel : UserViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter
    private lateinit var userLayoutManager: LinearLayoutManager

    val myRole = role

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo()
        userAdapter = UserAdapter(myRole)
        userLayoutManager = LinearLayoutManager(activity)
        binding.recyclerUser.apply {
            adapter = userAdapter
            layoutManager = userLayoutManager
        }
        binding.buttonModify.setOnClickListener {
            val param = UserInfoEditRequest(
                binding.textUsername.text.toString(),
                viewModel.userInfo.value!!.email,
                binding.textFirstname.text.toString(),
                binding.textLastname.text.toString()
            )
            viewModel.editUserInfo(param)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.userInfo.observe(viewLifecycleOwner, {
            binding.textUsername.setText(it.username)
            binding.textFirstname.setText(it.first_name)
            binding.textLastname.setText(it.last_name)
            if(myRole=="participant") {
                userAdapter.setMySeminars(it.participant!!.seminars)
            }
            else if(myRole=="instructor") {
                val builder = StringBuilder()
                builder.append("내가 진행중인 세미나: ")
                if(it.instructor?.charge!=null) {
                    builder.append(it.instructor.charge.name)
                }
                binding.textCharge.text = builder.toString()
                binding.textRecyclerViewTitle.text = ""
            }
        })
    }
}