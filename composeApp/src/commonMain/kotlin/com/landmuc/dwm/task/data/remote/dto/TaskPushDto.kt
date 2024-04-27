package com.landmuc.dwm.task.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskPushDto(
    val taskGroup: String,
    val taskTitle: String,
    val taskFurtherInformation: String?,
    val dateDue: String?,
    val isDone: Boolean = false
)