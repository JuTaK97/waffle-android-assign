package com.example.assignment2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.ItemTodoBinding
import com.example.assignment2.model.Todo

class TodoAdapter(private val callbackInterface: CallbackInterface) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private var todos: List<Todo> = listOf()

    inner class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val data = todos[position]
        holder.binding.apply {
            textTitle.text = data.title
            textDetail.text = data.content
        }
        holder.binding.deleteButton.setOnClickListener {
            callbackInterface.callback(todos[position])
        }
    }
    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodos(todos: List<Todo>) {
        this.todos =todos
        this.notifyDataSetChanged()
    }
}