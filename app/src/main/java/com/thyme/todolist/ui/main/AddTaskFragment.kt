package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.thyme.todolist.data.Database
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.ui.toDo.list.TaskListViewModel
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
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
        binding!!.addTaskFragment?.AddTaskButton?.setOnClickListener { addNew() }


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

    fun addNew() {

        var name = binding?.addTaskFragment!!.editTextTextTaskName.text.toString()
        var date = binding?.addTaskFragment!!.editTextDate.text.toString()
        var description = binding?.addTaskFragment!!.editTextTextDescription.text.toString()
        var nameAndDate = name + "\n" + date
        var hour = binding?.addTaskFragment!!.editTextTime.text.toString()

        var task = Task(name,date,description,nameAndDate,hour)
        Database.taskDao.addTask(task)
        Toast.makeText(requireActivity(), "Task added", Toast.LENGTH_SHORT).show()

    }





}