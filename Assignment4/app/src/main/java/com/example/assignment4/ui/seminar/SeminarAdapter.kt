package com.example.assignment4.ui.seminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment4.databinding.SeminarInfoInstructorBinding
import com.example.assignment4.databinding.SeminarInfoOtherBinding
import com.example.assignment4.databinding.SeminarInfoParticipantBinding
import timber.log.Timber
import java.lang.IllegalStateException

class SeminarAdapter (
    private val callbackInterface: CallbackInterface): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ParticipantSeminarHolder(var binding1 : SeminarInfoParticipantBinding) :RecyclerView.ViewHolder(binding1.root)
    inner class InstructorSeminarHolder(var binding2 : SeminarInfoInstructorBinding) :RecyclerView.ViewHolder(binding2.root)
    inner class OtherSeminarHolder(var binding3 : SeminarInfoOtherBinding) :RecyclerView.ViewHolder(binding3.root)
    private var seminars : List<Seminar> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
           PARTICIPANT ->  {
                val binding = SeminarInfoParticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ParticipantSeminarHolder(binding)
           }
            INSTRUCTOR -> {
                val binding = SeminarInfoInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                InstructorSeminarHolder(binding)
            }
            OTHER -> {
                val binding = SeminarInfoOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OtherSeminarHolder(binding)
            }
            else -> throw IllegalStateException("Illegal viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val seminar = seminars[position]
        val nameBuilder = StringBuilder()
        for(instructor in seminar.instructors) {
            nameBuilder.append(instructor.username)
        }
        when(holder) {
            is ParticipantSeminarHolder -> {
                holder.binding1.textInstructor.text = nameBuilder.toString()
                holder.binding1.textName.text = seminar.name
            }
            is InstructorSeminarHolder -> {
                holder.binding2.textInstructor.text = nameBuilder.toString()
                holder.binding2.textName.text = seminar.name
            }
            is OtherSeminarHolder -> {
                holder.binding3.textInstructor.text = nameBuilder.toString()
                holder.binding3.textName.text = seminar.name
            }
        }
    }

    override fun getItemCount(): Int {
        return seminars.size
    }

    override fun getItemViewType(position: Int): Int {
        val role = callbackInterface.callBack()
        Timber.d("Callback: my role is $role")
        return when(role){
            "participant"-> PARTICIPANT
            "instructor" -> INSTRUCTOR
            "other" -> OTHER
            else -> -1
        }
    }

    fun setSeminars(seminars : List<Seminar>?) {
        if(seminars != null) {
            this.seminars = seminars
        }
        this.notifyDataSetChanged()
    }
    companion object {
        var PARTICIPANT : Int = 1
        var INSTRUCTOR : Int = 2
        var OTHER : Int = 3
    }
}