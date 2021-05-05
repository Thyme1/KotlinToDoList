package com.thyme.todolist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.room.Database
import com.thyme.todolist.R
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.AddTaskFragmentBinding
import com.thyme.todolist.ui.base.BaseFragment
import com.thyme.todolist.viewmodels.TaskListViewModel
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
import com.thyme.todolist.utils.ImageUtils
import com.thyme.todolist.viewmodels.AddTaskViewModel
import com.thyme.todolist.viewmodels.AddTaskViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskFragment : BaseFragment() {

    private val args: AddTaskArgs by navArgs()
    private var mBinding: AddTaskFragmentBinding? = null

    @Inject
    lateinit var addTaskViewModelFactory: AddTaskViewModelFactory

    private val addTaskViewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.provideFactory(addTaskViewModelFactory, args.chapterUid)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = DataBindingUtil.inflate<AddTaskFragmentBinding>(
                inflater, R.layout.add_task_fragment, container, false
        )

        mBinding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            addTaskModel = addTaskViewModel
            addTaskFragment = this@AddTaskFragment
        }

        observeModelNavigation(addTaskViewModel)
    }

}


    fun addNew() {

       

    }





}