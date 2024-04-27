package com.landmuc.dwm.task.data.repository

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.task.data.mapper.toTask
import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.data.remote.dto.TaskPushDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import io.github.jan.supabase.postgrest.from

class TaskDataRepositoryImpl(
    private val client: SupabaseClient
): TaskDataRepository {
    override suspend fun getTasks(tableName: String): List<Task> {
        return client.supabaseClient.from(tableName).select().decodeList<TaskDto>().map { taskDto -> taskDto.toTask() }
    }

    override suspend fun addTask(
        tableName: String,
        taskGroup: String,
        taskTitle: String,
        taskFurtherInformation: String?,
        dateDue: String?,
        isDone: Boolean
    ) {
        val task = TaskPushDto(
            taskGroup = taskGroup,
            taskTitle = taskTitle,
            taskFurtherInformation = taskFurtherInformation,
            dateDue = dateDue,
            isDone = isDone
        )

        client.supabaseClient.from(tableName).insert(task)
    }

    override suspend fun deleteTask(tableName: String, taskId: Int) {
        client.supabaseClient.from(tableName).delete {
            filter { eq("taskId", taskId) }
        }
    }
}