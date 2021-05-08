package com.thyme.todolist.ui.toDo.list.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thyme.todolist.R
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.ItemTaskBinding
import com.thyme.todolist.viewmodels.TaskListViewModel

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>() {


    inner class ToDoViewHolder(val binding: ItemTodoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    var mTodo: List<Task>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            ItemTodoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = mTodo[position]

        holder.binding.apply {
            textView.text = currentToDo.toDoTitle
        }

        holder.binding.cbTodo.apply {
            setOnClickListener {
                holder.binding.apply {
                    if (isChecked) {
                        textView.paintFlags =
                            textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        textView.paintFlags =
                            textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }
        }
    }

    override fun getItemCount() = mTodo.size


}