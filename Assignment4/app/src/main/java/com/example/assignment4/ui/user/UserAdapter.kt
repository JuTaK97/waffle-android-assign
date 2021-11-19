package com.example.assignment4.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.databinding.InstructorSeminarBinding
import com.example.assignment4.databinding.ParticipantSeminarBinding
import timber.log.Timber
import java.lang.IllegalStateException

class UserAdapter(
    private val myRole : String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ParticipantSeminar(var binding: ParticipantSeminarBinding ) :RecyclerView.ViewHolder(binding.root)
    inner class InstructorSeminar(var binding: InstructorSeminarBinding ) :RecyclerView.ViewHolder(binding.root)
    private var mySeminars : List<MySeminar> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                val binding = ParticipantSeminarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ParticipantSeminar(binding)
            }
            2 -> {
                val binding = InstructorSeminarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                InstructorSeminar(binding)
            }
            else -> throw IllegalStateException("Illegal viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ParticipantSeminar-> {
                holder.binding.textTitle.text = mySeminars[position].name
                holder.binding.textJoin.text = mySeminars[position].joined_at
            }
        }
    }

    override fun getItemCount(): Int {
        return mySeminars.size
    }

    override fun getItemViewType(position: Int): Int {
        val role = myRole
        Timber.d("UserAdapterCallback: my role is $role")
        return when(role){
            "participant"-> 1
            "instructor" -> 2
            "other" -> 3
            else -> -1
        }
    }
    fun setMySeminars(mySeminars : List<MySeminar>) {
        this.mySeminars = mySeminars
        notifyDataSetChanged()
    }


}