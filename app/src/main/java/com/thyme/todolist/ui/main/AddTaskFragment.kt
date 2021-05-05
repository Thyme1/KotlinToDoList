package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lsm.learnwordspart2.R
import com.lsm.learnwordspart2.databinding.AddTaskFragmentBinding
import com.thyme.todolist.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddTaskFragment : Fragment(R.layout.add_task_fragment) {

    private val viewModel: AddTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = AddTaskFragmentBinding.bind(view)

        binding.apply {
            editTextTaskName.setText(viewModel.taskName)
            editTextDate.setText(viewModel.taskDate)
            editTextTextDescription.setText(viewModel.taskDescription)
            editTextTime.setText(viewModel.taskTime)


            editTextTaskName.addTextChangedListener {
                viewModel.taskName = it.toString()
            }



            fabSaveTask.setOnClickListener {
                viewModel.onSaveClick()
            }
        }


    }
}








