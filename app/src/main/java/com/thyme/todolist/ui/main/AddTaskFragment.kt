package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.thyme.todolist.R
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.ui.base.BaseFragment
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
import com.thyme.todolist.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddTaskFragment : BaseFragment() {
    private var mBinding: AddTaskFragmentBinding? = null
    lateinit var adapter: TaskAdapter

    private val viewModel: AddTaskViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<AddTaskFragmentBinding>(
            inflater, R.layout.add_task_fragment, container, false
        )

        this.mBinding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            addTaskFragment = this@AddTaskFragment
        }

    }

}








