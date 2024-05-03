package com.landmuc.dwm.task.data.repository

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.data.remote.dto.TaskPushDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import io.github.jan.supabase.postgrest.postgrest

class TaskDataRepositoryImpl(
    private val client: SupabaseClient
): TaskDataRepository {
    override suspend fun getTasks(tableName: String): List<TaskDto> {
        return client.supabaseClient.postgrest.from(tableName).select().decodeList<TaskDto>()
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

        client.supabaseClient.postgrest.from(tableName).insert(task)
    }

    override suspend fun deleteTask(tableName: String, taskId: Int) {
        client.supabaseClient.postgrest.from(tableName).delete {
            filter { eq("taskId", taskId) }
        }
    }
    override suspend fun updateTask(
        tableName: String,
        task: Task,
        taskGroup: String?,
        taskTitle: String?,
        taskFurtherInformation: String?,
        dateCreated: String?,
        dateDue: String?,
        isDone: Boolean?
        ) {
        val updatedTaskGroup = taskGroup ?: task.taskGroup.name
        val updatedTaskTitle = taskTitle ?: task.taskTitle
        val updatedTaskFurtherInformation = taskFurtherInformation ?: task.taskFurtherInformation
        val updatedDateDue = dateDue ?: task.dateDue
        val updatedIsDone = isDone ?: task.isDone

        client.supabaseClient.postgrest.from(tableName).update(
            {
                set("taskGroup", updatedTaskGroup)
                set("taskTitle", updatedTaskTitle)
                set("taskFurtherInformation", updatedTaskFurtherInformation)
                set("dateDue", updatedDateDue)
                set("isDone", updatedIsDone)
            }
        ) { filter { eq("taskId", task.taskId) } }
    }

}