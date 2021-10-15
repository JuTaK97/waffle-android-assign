package com.wafflestudio.assignment3.ui.main


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.assignment3.databinding.ItemMemberIosBinding
import com.wafflestudio.assignment3.databinding.ItemMemberWaffleBinding
import com.wafflestudio.assignment3.model.Member
import com.wafflestudio.assignment3.ui.detail.DetailActivity
import java.lang.IllegalStateException

class MemberAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var members : List<Member> = listOf()

    inner class MemberWaffleViewHolder(var binding: ItemMemberWaffleBinding) : RecyclerView.ViewHolder(binding.root)
    inner class MemberIosViewHolder(var binding: ItemMemberIosBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> {
                val binding = ItemMemberWaffleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MemberWaffleViewHolder(binding)
            }
            1 -> {
                val binding = ItemMemberIosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MemberIosViewHolder(binding)
            }
            else -> throw IllegalStateException("Illegal viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val member = members[position]
        when(holder) {
            is MemberWaffleViewHolder -> {
                holder.binding.apply{
                    textName.text = member.name
                    teamName.text = member.team
                }
            }
            is MemberIosViewHolder -> {
                holder.binding.apply {
                    textName.text = member.name
                    teamName.text = member.team
                }
            }
        }
        holder.itemView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", member.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun getItemViewType(position: Int): Int {
        val member = members[position]
        return when(member.team) {
            "waffle" -> 0
            "iOS" -> 1
            else -> 2
        }
    }

    fun setMembers(members : List<Member>) {
        this.members = members
        this.notifyDataSetChanged()
    }

}
