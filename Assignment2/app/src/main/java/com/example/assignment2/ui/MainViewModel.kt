package com.example.assignment2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2.App
import com.example.assignment2.model.Todo
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :AndroidViewModel(application){

    private val todoRepository by lazy { (application as App).todoRepository }

    fun observeTodo() = todoRepository.getTodo()

    fun addTodo(title: String, detail:String) {
        viewModelScope.launch {
            todoRepository.addTodo(Todo(title, detail))
        }
    }

    fun deleteTodo(todo : Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }






}