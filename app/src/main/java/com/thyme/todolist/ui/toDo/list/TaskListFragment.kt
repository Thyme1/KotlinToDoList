package com.thyme.todolist.ui.toDo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import com.thyme.todolist.R
import com.thyme.todolist.databinding.FragmentTaskListBinding
import com.thyme.todolist.ui.base.BaseFragment
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
import com.thyme.todolist.viewmodels.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskListFragment : BaseFragment() {

    private var mBinding: FragmentTaskListBinding? = null
    lateinit var adapter: TaskAdapter
    val subjectViewModel: TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTaskListBinding>(
            inflater, R.layout.fragment_task_list, container, false
        )
        this.mBinding = binding
        adapter = TaskAdapter(subjectViewModel)
        subscribeUi(adapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
            viewModel = subjectViewModel
        }
        observeModelNavigation(subjectViewModel)
    }

    private fun subscribeUi(newAdapter: TaskAdapter) {
        subjectViewModel.tasks.observe(viewLifecycleOwner) { result ->
            newAdapter.submitList(result)
            newAdapter.notifyDataSetChanged()
        }
    }

}


