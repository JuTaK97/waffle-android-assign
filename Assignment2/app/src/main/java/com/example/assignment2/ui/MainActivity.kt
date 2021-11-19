package com.example.assignment2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment2.databinding.ActivityMainBinding
import com.example.assignment2.databinding.DialogAddTodoBinding
import com.example.assignment2.model.Todo

class MainActivity : AppCompatActivity(), CallbackInterface {

    private lateinit var binding : ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(this)
        todoLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewTodo.apply{
            adapter =todoAdapter
            layoutManager = todoLayoutManager
        }
        binding.addButton.setOnClickListener {
            showDialog()
        }
        viewModel.observeTodo().observe(this,{
            todoAdapter.setTodos(it)
        })

    }
    private fun showDialog() {
        val dialogBinding = DialogAddTodoBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Add Todos")
            .setView(dialogBinding.root)
            .setPositiveButton("create") { _, _ ->
                viewModel.addTodo(
                    dialogBinding.dialogTextTitle.text.toString(),
                    dialogBinding.dialogTextContent.text.toString()
                )
                Toast.makeText(applicationContext, "Create", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("cancel") { _, _ ->
                Toast.makeText(applicationContext, "Cancel", Toast.LENGTH_SHORT).show()
            }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun callback(todo: Todo) {
        viewModel.deleteTodo(todo)
    }
}