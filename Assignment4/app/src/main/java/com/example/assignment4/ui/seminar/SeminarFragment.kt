package com.example.assignment4.ui.seminar

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment4.databinding.FragmentSeminarBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SeminarFragment : Fragment(), CallbackInterface {

    private lateinit var binding : FragmentSeminarBinding
    private val viewModel : SeminarViewModel by activityViewModels()

    private lateinit var seminarAdapter: SeminarAdapter
    private lateinit var seminarLayoutManager : LinearLayoutManager
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeminarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSeminarList()
        val role = viewModel.getRole()
        sharedPreferences.edit {
            putString("role", role)
        }

        seminarAdapter = SeminarAdapter(this)
        seminarLayoutManager =LinearLayoutManager(activity)
        binding.recyclerSeminar.apply {
            adapter = seminarAdapter
            layoutManager = seminarLayoutManager
        }
        viewModel.seminarList.observe(viewLifecycleOwner) {
            seminarAdapter.setSeminars(it)
        }
    }
    override fun callBack(): String {
        val role = sharedPreferences.getString("role","error").toString()
        Timber.d("CALLBACK: $role")
        return role
    }

}