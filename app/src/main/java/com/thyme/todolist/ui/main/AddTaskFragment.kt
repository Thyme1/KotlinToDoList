package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
import com.thyme.todolist.viewmodels.TaskListViewModel
import kotlinx.android.synthetic.main.add_task_fragment.*

class AddTaskFragment : Fragment() {
    private val sharedViewModel: TaskListViewModel by activityViewModels()
    private var binding: AddTaskFragmentBinding? = null
    lateinit var mAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = AddTaskFragmentBinding.inflate(
            inflater, container, false
        )

        binding = fragmentBinding
        binding!!.addTaskFragment?.AddTaskButton?.setOnClickListener { }


        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addTaskFragment = this@AddTaskFragment
        }
    }





}