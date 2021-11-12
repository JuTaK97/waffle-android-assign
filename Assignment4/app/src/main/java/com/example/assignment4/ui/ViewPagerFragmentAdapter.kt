package com.example.assignment4.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignment4.ui.seminar.SeminarFragment
import com.example.assignment4.ui.user.UserFragment

class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    val fragmentList = listOf(SeminarFragment(),UserFragment())
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    override fun getItemCount(): Int {
        return fragmentList.size
    }
}