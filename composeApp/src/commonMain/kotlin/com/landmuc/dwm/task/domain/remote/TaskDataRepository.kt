package com.landmuc.dwm.task.domain.remote

import com.landmuc.dwm.task.domain.model.Task

interface TaskDataRepository {
    suspend fun getTasks(tableName: String): List<Task>
    suspend fun addTask(
        tableName: String,
        taskGroup: String,
        taskTitle: String,
        taskFurtherInformation: String?,
        dateDue: String?,
        isDone: Boolean
    )

    suspend fun deleteTask(tableName: String, taskId: Int)
}