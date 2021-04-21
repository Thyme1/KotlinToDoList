package com.thyme.todolist.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.ui.toDo.list.TaskListViewModel

class AddTaskFragment : Fragment() {
    private val sharedViewModel: TaskListViewModel by activityViewModels()
    private var binding: AddTaskFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = AddTaskFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        binding!!.addButton.setOnClickListener { addToSharedPref() }



        return fragmentBinding.root
    }

    private fun addToSharedPref() {
        val sharedPreference =  getSharedPreferences("AddTaskData", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("username","Anupam")
        editor.putLong("l",100L)
        editor.commit()
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