package com.landmuc.dwm.task.data.repository

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.core.remote.SupabasePostgrest
import com.landmuc.dwm.task.data.mapper.toTask
import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.data.remote.dto.TaskPushDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class TaskDataRepositoryImpl(
    private val postgrest: SupabasePostgrest
): TaskDataRepository {
    override suspend fun getTasks(tableName: String): List<TaskDto> {
        return postgrest.postgrest.from(tableName).select().decodeList<TaskDto>()
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

        postgrest.postgrest.from(tableName).insert(task)
    }

    override suspend fun deleteTask(tableName: String, taskId: Int) {
        postgrest.postgrest.from(tableName).delete {
            filter { eq("taskId", taskId) }
        }
    }
}