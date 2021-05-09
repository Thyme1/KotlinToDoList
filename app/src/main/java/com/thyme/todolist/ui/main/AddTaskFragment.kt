package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.thyme.todolist.R
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment(R.layout.add_task_fragment) {

    private var _binding: AddTaskFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener { mView ->
            saveNote(mView)
        }

        binding.cancelButton.setOnClickListener {
            binding.editTextTaskName.text.clear()
            view.findNavController().navigate(
                R.id.action_addTaskFragment_to_taskListFragment
            )
        }
    }

    private fun saveNote(view: View) {
        val todoName = binding.editTextTaskName.text.toString()
        val taskDescription = binding.editTextTaskDescription.text.toString()
        val taskDate = binding.editTextTaskDate.text.toString()
        val taskTime = binding.editTextTaskTime.text.toString()


        if (todoName.isNotEmpty()) {
            val todo = Task(
                0,
                name = todoName,
                date = taskDate,
                time = taskTime,
                description = taskDescription
            )

            viewModel.insertTask(todo)

            Snackbar.make(
                view, "Task Saved Successfully",
                Snackbar.LENGTH_SHORT
            ).show()

            view.findNavController().navigate(
                R.id.action_addTaskFragment_to_taskListFragment
            )

        } else {
            val toast = Toast.makeText(
                activity,
                "Task title can not be empty",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}






