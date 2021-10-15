package com.wafflestudio.assignment3.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wafflestudio.assignment3.App
import com.wafflestudio.assignment3.model.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOError

class MainViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val memberRepository by lazy { (application as App).memberRepository }

    private val _list = MutableLiveData<List<Member>>()
    val list : LiveData<List<Member>> = _list

    fun observeMember() : LiveData<List<Member>> {
        viewModelScope.launch {
            _list.value = memberRepository.getAllMember()
        }
        return list
    }

    fun fetchMemberList() {
        viewModelScope.launch {
            try {
                memberRepository.fetchAllMember()
            } catch (e: IOError) {
                Timber.e(e)
            }
        }
    }


}
