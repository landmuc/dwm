package com.landmuc.dwm.task.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.task.data.mapper.toTask
import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.model.TaskGroup
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.decodeRecord
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(SupabaseExperimental::class)
class TaskScreenModel(
    private val taskRep: TaskDataRepository,
    private val client: SupabaseClient
): ScreenModel {

    private val _taskTitle = MutableStateFlow("")
    val taskTitle = _taskTitle.asStateFlow()

    private val _taskFurtherInformation = MutableStateFlow("")
    val taskFurtherInformation = _taskFurtherInformation.asStateFlow()

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    private val _taskList: MutableStateFlow<List<Task>> = MutableStateFlow(listOf())
    val taskList = _taskList.asStateFlow()


//    init {
//        subscribeToChannel()
//    }

    val channel = client.supabaseClient.realtime.channel("taskChannel")
//    val getFlow =
//        screenModelScope.launch {
//            val taskDtoListFlow: Flow<List<TaskDto>> = channel.postgresListDataFlow(
//                schema = "public",
//                table = "tasks",
//                primaryKey = TaskDto::taskId
//            )
//
//            taskDtoListFlow.collect { serverTaskList ->
//                val updatedTaskList: List<Task> = serverTaskList.map { taskDto -> taskDto.toTask() }
//                _taskList.update { updatedTaskList }
//            }
//
//        }
//
//
//    fun subscribeToChannel() {
//        screenModelScope.launch {
//            channel.subscribe()
//        }
//    }

    fun connectToRealTime() {
        screenModelScope.launch {
        channel.postgresChangeFlow<PostgresAction>("public") { table = "tasks"}.onEach {
            when(it) {
                is PostgresAction.Delete -> error("Delete should not be possible")
                is PostgresAction.Insert -> _taskList.value += it.decodeRecord<TaskDto>().toTask()
                is PostgresAction.Select -> error("Select should not be possible")
                is PostgresAction.Update -> error("Update should not be possible")
            }
        }.launchIn(screenModelScope)

        channel.subscribe()
        }
    }


    fun getTasks() {
        screenModelScope.launch {
            val list = taskRep.getTasks(tableName = "tasks").map { taskDto -> taskDto.toTask() }
            _taskList.value = list
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
        screenModelScope.launch {
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