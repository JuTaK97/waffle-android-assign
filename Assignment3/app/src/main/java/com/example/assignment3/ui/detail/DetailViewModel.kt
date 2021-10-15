package com.wafflestudio.assignment3.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wafflestudio.assignment3.App
import com.wafflestudio.assignment3.model.Member
import kotlinx.coroutines.launch

class DetailViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val memberRepository by lazy { (application as App).memberRepository }

    private val _member = MutableLiveData<Member>()
    val member : LiveData<Member> = _member


    fun fetchMember(id : Int) : LiveData<Member> {
        viewModelScope.launch {
            _member.value = memberRepository.fetchDetail(id)
        }
        return member
    }

}
