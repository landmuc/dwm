package com.landmuc.dwm.task.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskScreenModel(
    private val taskRep: TaskDataRepository
): ScreenModel {

    private val _taskTitle = MutableStateFlow("")
    val taskTitle = _taskTitle.asStateFlow()

    private val _taskFurtherInformation = MutableStateFlow("")
    val taskFurtherInformation = _taskFurtherInformation.asStateFlow()

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    private val _taskList: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val taskList = _taskList.asStateFlow().stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList<Task>()
    )

    private val getTasksJob = screenModelScope.launch {
        val serverTaskList = taskRep.getTasks(tableName = "tasks")
        _taskList.update { serverTaskList }
    }

    fun addTask() {
        screenModelScope.launch {
        taskRep.addTask(
            tableName = "tasks",
            taskGroup = "DAY",
            taskTitle = _taskTitle.value,
            taskFurtherInformation = _taskFurtherInformation.value,
            dateDue = null,
            isDone = false
        )
        }
    }
    fun updateTaskTitle(taskTitle: String) {
        _taskTitle.update { taskTitle }
    }
    fun updateTaskFurtherInformation(taskFurtherInformation: String) {
        _taskFurtherInformation.update { taskFurtherInformation }
    }
    fun onExpand() {
        _isExpanded.update { !_isExpanded.value }
    }

}