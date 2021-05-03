package com.thyme.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thyme.todolist.ui.base.NavigEvent
import com.thyme.todolist.ui.base.NavigateEventInfo

open class BaseViewModel : ViewModel() {
    internal val _navigateToFragment = MutableLiveData<NavigEvent<NavigateEventInfo>>()
    val navigateToFragment: LiveData<NavigEvent<NavigateEventInfo>>
        get() = _navigateToFragment

}