package com.landmuc.dwm.task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.task.data.mapper.toTask
import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
@OptIn(SupabaseExperimental::class)
class TaskViewModel(
    private val taskRep: TaskDataRepository,
    private val client: SupabaseClient
): ViewModel() {

    private val _taskTitle = MutableStateFlow("")
    val taskTitle = _taskTitle.asStateFlow()

    private val _taskFurtherInformation = MutableStateFlow("")
    val taskFurtherInformation = _taskFurtherInformation.asStateFlow()

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    private val _taskList: MutableStateFlow<List<Task>> = MutableStateFlow(listOf())
    val taskList = _taskList.asStateFlow()

    private val _channelIsSubscribed = MutableStateFlow(false)
    val channelIsSubscribed = _channelIsSubscribed.asStateFlow()



    val channel = client.supabaseClient.realtime.channel("taskChannel")
    fun getFlow() {
        viewModelScope.launch {
            val taskDtoListFlow: Flow<List<TaskDto>> = channel.postgresListDataFlow(
                schema = "public",
                table = "tasks",
                primaryKey = TaskDto::taskId
            )

            taskDtoListFlow.collect { serverTaskList ->
                val updatedTaskList: List<Task> = serverTaskList.map { taskDto -> taskDto.toTask() }
                _taskList.update { updatedTaskList }
            }

        }
    }


    fun subscribeToChannel() {
        viewModelScope.launch {
            channel.subscribe()
            _channelIsSubscribed.update { true }
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            val list = taskRep.getTasks(tableName = "tasks").map { taskDto -> taskDto.toTask() }
            _taskList.value = list
        }
    }


    fun addTask() {
        viewModelScope.launch {
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
        viewModelScope.launch {
            delay(1000L)
            _taskTitle.update { "" }
            _taskFurtherInformation.update { "" }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            taskRep.deleteTask(tableName = "tasks", taskId = taskId)
        }
    }
    fun updateTaskTitle(taskTitle: String) {
        _taskTitle.update { taskTitle }
    }
    fun updateTaskFurtherInformation(taskFurtherInformation: String) {
        _taskFurtherInformation.update { taskFurtherInformation }
    }
    fun updateTask(
        task: Task,
        taskGroup: String? = null,
        taskTitle: String? = null,
        taskFurtherInformation: String? = null,
        dateDue: String? = null,
        isDone: Boolean? = null
        ) {
        viewModelScope.launch {
        taskRep.updateTask(
            tableName = "tasks",
            task = task,
            taskGroup = taskGroup,
            taskTitle = taskTitle,
            taskFurtherInformation = taskFurtherInformation,
            dateDue = dateDue,
            isDone = isDone
            )
        }
    }
    fun onExpand() { _isExpanded.update { !_isExpanded.value } }

}

