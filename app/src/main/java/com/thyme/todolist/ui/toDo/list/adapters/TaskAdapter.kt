package com.thyme.todolist.ui.toDo.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lsm.learnwordspart2.R
import com.lsm.learnwordspart2.databinding.ItemTaskBinding
import com.thyme.todolist.data.Task
import com.thyme.todolist.viewmodels.TaskListViewModel

class TaskAdapter internal constructor(
    private val mSubjectViewModel : TaskListViewModel
): ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, mSubjectViewModel)
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

private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.name == newItem.name

    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}

