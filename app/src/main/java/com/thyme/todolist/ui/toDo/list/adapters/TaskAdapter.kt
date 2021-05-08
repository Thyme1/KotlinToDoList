package com.thyme.todolist.ui.toDo.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thyme.todolist.R
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.ItemTaskBinding
import com.thyme.todolist.viewmodels.TaskListViewModel

class TaskAdapter internal constructor(
    private val mSubjectViewModel : TaskListViewModel
): ListAdapter<Task, TaskAdapter.TaskViewHolder>(SubjectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val subject = getItem(position)
        holder.bind(subject, mSubjectViewModel)
    }

    class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentTask: Task, taskViewModel: TaskListViewModel) {
            binding.task = currentTask
            binding.taskViewModel = taskViewModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemTaskBinding = DataBindingUtil.inflate(
                    layoutInflater, R.layout.item_task,
                    parent, false
                )
                return TaskViewHolder(binding)

            }
        }
    }
}


private class SubjectDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}