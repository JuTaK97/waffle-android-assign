package com.example.assignment2

import android.app.Application
import com.example.assignment2.db.TodoDatabase
import com.example.assignment2.repository.TodoRepository

class App : Application() {

    private val todoDatabase by lazy { TodoDatabase.getInstance(this) }
    val todoRepository by lazy { TodoRepository.getInstance(todoDatabase.todoDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}