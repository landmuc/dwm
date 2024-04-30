package com.landmuc.dwm.task.data.repository

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.task.data.mapper.toTask
import com.landmuc.dwm.task.data.remote.dto.TaskDto
import com.landmuc.dwm.task.data.remote.dto.TaskPushDto
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.decodeOldRecord
import io.github.jan.supabase.realtime.decodeRecord
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    suspend fun getTaskList(): Flow<Task> {
        val channel = client.supabaseClient.realtime.channel("taskChannel")
        val changeFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public") {
            table = "tasks"
        }.map {
            when(it) {
                is PostgresAction.Insert -> it.decodeRecord<TaskDto>().toTask()
                is PostgresAction.Delete -> it.decodeOldRecord<TaskDto>().toTask()
                is PostgresAction.Update -> it.decodeRecord<TaskDto>().toTask()
                is PostgresAction.Select -> error("Select should not be possible")
            }
        }
        //channel.subscribe()
        return changeFlow
    }


}