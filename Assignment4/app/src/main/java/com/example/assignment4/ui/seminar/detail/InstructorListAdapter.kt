package com.example.assignment4.ui.seminar.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.databinding.InstructorDetailBinding
import com.example.assignment4.databinding.ParticipantDetailBinding
import timber.log.Timber

class InstructorListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class InstructorDetail(var binding: InstructorDetailBinding) : RecyclerView.ViewHolder(binding.root)

    var instructorList : List<DetailInstructor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = InstructorDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstructorDetail(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is InstructorDetail -> {
                holder.binding.detailInstructorUsername.text = instructorList[position].username
                holder.binding.detailInstructorEmail.text = instructorList[position].email
            }
        }
    }

    override fun getItemCount(): Int {
        return instructorList.size
    }

    fun setDetailInstructorList(userList: List<DetailInstructor>) {
        this.instructorList = userList
        this.notifyDataSetChanged()
    }

}