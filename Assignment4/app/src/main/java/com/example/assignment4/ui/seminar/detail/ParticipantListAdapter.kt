package com.example.assignment4.ui.seminar.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.databinding.ParticipantDetailBinding
import com.example.assignment4.dto.DetailParticipant
import timber.log.Timber

class ParticipantListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ParticipantDetail(var binding: ParticipantDetailBinding) : RecyclerView.ViewHolder(binding.root)

    private var userList : List<DetailParticipant> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ParticipantDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParticipantDetail(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ParticipantDetail -> {
                holder.binding.detailUsername.text = userList[position].username
                holder.binding.detailEmail.text = userList[position].email
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setDetailParticipantList(userList: List<DetailParticipant>) {
        if(!userList.isEmpty()) {
            this.userList = userList
        }
        else {
            val empty = DetailParticipant(0, "Empty","","","","",true,"")
            this.userList = listOf(empty)
            notifyItemChanged(0)
        }
        notifyDataSetChanged()
    }

}