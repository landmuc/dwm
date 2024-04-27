package com.landmuc.dwm.task.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val taskId: Int,
    val taskGroup: String,
    val taskTitle: String,
    val taskFurtherInformation: String?,
    val dateCreated: String,
    val dateDue: String?,
    val isDone: Boolean
)