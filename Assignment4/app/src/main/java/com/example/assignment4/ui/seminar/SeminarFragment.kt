package com.example.assignment4.ui.seminar


import android.content.Intent
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
import com.example.assignment4.login.LoginActivity
import com.example.assignment4.ui.seminar.create.CreateSeminarActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SeminarFragment(role : String) : Fragment() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding : FragmentSeminarBinding
    private val viewModel : SeminarViewModel by activityViewModels()

    private lateinit var seminarAdapter: SeminarAdapter
    private lateinit var seminarLayoutManager : LinearLayoutManager
    val myRole = role

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
        seminarAdapter = SeminarAdapter(myRole)
        if(myRole=="instructor") {
            binding.fab.show()
            binding.fab.setOnClickListener {
                val intent = Intent(activity, CreateSeminarActivity::class.java)
                intent.putExtra("role", myRole)
                startActivity(intent)
            }
        }
        else {
            binding.fab.hide()
        }
        seminarLayoutManager =LinearLayoutManager(activity)
        binding.recyclerSeminar.apply {
            adapter = seminarAdapter
            layoutManager = seminarLayoutManager
        }
        viewModel.seminarList.observe(viewLifecycleOwner) {
            seminarAdapter.setSeminars(it)
        }
        binding.buttonLogout.setOnClickListener {
            sharedPreferences.edit {
                this.remove("role")
                this.remove("token")
                this.commit()
            }
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
            activity?.finish()
        }
    }

}