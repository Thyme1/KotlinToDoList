package com.thyme.todolist.ui.toDo.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thyme.todolist.R
import com.thyme.todolist.databinding.FragmentTaskListBinding
import com.thyme.todolist.viewmodels.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskListFragment : Fragment(R.layout.fragment_task_list) {

    private val viewModel: TaskListViewModel by viewModels()

    private lateinit var searchView: SearchView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTaskListBinding.bind(view)


        binding.fabAddTask.setOnClickListener {
            viewModel.onAddNewTaskClick()
        }












    }
}


