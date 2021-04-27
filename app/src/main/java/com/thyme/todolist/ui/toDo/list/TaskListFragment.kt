package com.thyme.todolist.ui.toDo.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.thyme.todolist.MainApplication
import com.thyme.todolist.R
import com.thyme.todolist.data.Database
import com.thyme.todolist.data.Repository
import com.thyme.todolist.data.Task
import com.thyme.todolist.databinding.FragmentTaskListBinding
import com.thyme.todolist.ui.main.MainViewModel
import com.thyme.todolist.ui.toDo.list.adapters.TaskAdapter
import com.thyme.todolist.ui.toDo.list.adapters.TaskItemClickListener


/**
 * A simple [Fragment] subclass.
 * Use the [SubjectListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment(), TaskItemClickListener {

    companion object {
        val TAG = "Task"
    }

    lateinit var mAdapter: TaskAdapter
    val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentTaskListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentTaskListBinding.inflate(
                inflater, container, false
        )
        binding = fragmentBinding

        mAdapter = TaskAdapter(this)
        mAdapter.submitList(Repository.getAllTasks().value)
        fragmentBinding.recyclerView.adapter = mAdapter

        subscribeUi(mAdapter, binding!!)

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            taskListFragment = this@TaskListFragment
        }
    }

    private fun subscribeUi(newAdapter: TaskAdapter, binding: FragmentTaskListBinding) {
        binding.recyclerView.adapter = newAdapter
        newAdapter.notifyDataSetChanged()

    }

    override fun chooseTask(task: Task) {
        Log.d("Do chore", "Task name clicked: ${task.name}")
        var bundle = Bundle()
        bundle.putString(TAG,task.name)
        Toast.makeText(requireActivity(), "You have choosen task: ${task.name}", Toast.LENGTH_LONG).show()
        }

    fun addTask() {
        findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
    }

    fun clearPreferences() {
        val context = MainApplication.applicationContext()
        Database.taskDao.clearSharedPreferences(context)
        Toast.makeText(requireActivity(), "SharedPreferences cleared", Toast.LENGTH_LONG).show()
        mAdapter.notifyDataSetChanged()

    }
    }

