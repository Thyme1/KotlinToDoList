package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.thyme.todolist.R
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.ui.base.BaseFragment
import com.thyme.todolist.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : BaseFragment() {

    private val viewModel: AddTaskViewModel by viewModels()
    private var mBinding: AddTaskFragmentBinding? = null









    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = DataBindingUtil.inflate<AddTaskFragmentBinding>(
            inflater, R.layout.add_task_fragment, container, false
        )

        mBinding = fragmentBinding

        return fragmentBinding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = AddTaskFragmentBinding.bind(view)

        binding.apply {
            editTextTaskName.setText(viewModel.taskName)
            editTextTaskDescription.setText(viewModel.taskDescription)
            editTextTaskDate.setText(viewModel.taskDate)
            editTextTaskTime.setText(viewModel.taskTime)

            editTextTaskName.addTextChangedListener {
                viewModel.taskName = it.toString()
            }

            editTextTaskDescription.addTextChangedListener {
                viewModel.taskDescription = it.toString()
            }
            editTextTaskDate.addTextChangedListener {
                viewModel.taskDate = it.toString()
            }
            editTextTaskTime.addTextChangedListener {
                viewModel.taskTime = it.toString()
            }


            saveButton.setOnClickListener {
                viewModel.onSaveClick()
            }
        }

    }
}




