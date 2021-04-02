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



class TaskAdapter internal constructor(
        private val mListener: TaskItemClickListener
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {

        return TaskViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, mListener)
    }

    override fun getItemCount(): Int {
        val result = super.getItemCount()
        return result
    }


    class TaskViewHolder(val binding: ItemTaskBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(currentTask: Task, listener: TaskItemClickListener) {
            binding.task = currentTask
            binding.clickListener = listener
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
interface TaskItemClickListener {
    fun chooseTask(task: Task)
}
private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}