package com.wafflestudio.assignment3.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.assignment3.databinding.ItemLectureBinding
import com.wafflestudio.assignment3.model.Lecture

class LectureAdapter : RecyclerView.Adapter<LectureAdapter.LectureViewHolder>() {

    inner class LectureViewHolder(val binding: ItemLectureBinding) : RecyclerView.ViewHolder(binding.root)
    private var lectures : List<Lecture> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val binding = ItemLectureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LectureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        val lecture = lectures[position]
        holder.binding.textCredit.text  = lecture.credit.toString()
        holder.binding.textInstructor.text = lecture.instructor
        holder.binding.textTitle.text = lecture.title
    }

    override fun getItemCount(): Int {
        return lectures.size
    }

    fun setLectures(lectures: List<Lecture>?) {
        if (lectures != null) {
            this.lectures = lectures
        }
        this.notifyDataSetChanged()
    }


}