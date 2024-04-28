package com.landmuc.dwm.task.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.task.data.mapper.toTask
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import kotlinx.coroutines.delay
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

    private val _taskList: MutableStateFlow<List<Task>> = MutableStateFlow(listOf())
    val taskList = _taskList.asStateFlow()

    init {
        getTaskList()
    }
    fun getTaskList() {
        screenModelScope.launch {
            val serverTaskList = taskRep.getTasks(tableName = "tasks")
            _taskList.emit(serverTaskList.map { taskDto -> taskDto.toTask() })
        }
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
        onExpand()
        // without the delay the web app would update the values to "" before addTask() could post the new task
        // works without delay on android and ios
        screenModelScope.launch {
            delay(1000L)
            _taskTitle.update { "" }
            _taskFurtherInformation.update { "" }
        }
    }

    fun deleteTask(taskId: Int) {
        screenModelScope.launch {
            taskRep.deleteTask(tableName = "tasks", taskId = taskId)
            getTaskList()
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