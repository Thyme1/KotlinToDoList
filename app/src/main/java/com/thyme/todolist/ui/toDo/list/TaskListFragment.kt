package com.thyme.todolist.ui.toDo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thyme.todolist.R
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.FragmentTaskListBinding
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
import com.thyme.todolist.viewmodels.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskListFragment : Fragment(R.layout.fragment_task_list) {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoAdapter: TaskAdapter
    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.fabAddTask.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_taskListFragment_to_addTaskFragment
            )
        }
    }

    private fun setupRecyclerView() {

        todoAdapter = TaskAdapter()

        binding.rvTodoList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }

        viewModel.allToDos.observe(requireActivity()) { listTodo ->
            updateUi(listTodo)
            todoAdapter.mTodo = listTodo
        }
    }

    private fun updateUi(list: List<Task>) {
        if (list.isNotEmpty()) {
            binding.rvTodoList.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE
        } else {
            binding.rvTodoList.visibility = View.GONE
            binding.cardView.visibility = View.VISIBLE
        }
    }


}


