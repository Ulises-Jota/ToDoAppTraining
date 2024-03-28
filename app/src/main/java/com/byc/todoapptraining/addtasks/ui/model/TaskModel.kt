package com.byc.todoapptraining.addtasks.ui.model

import com.google.gson.annotations.SerializedName

data class TaskModel(
    @SerializedName("id")
    val id: Int = System.currentTimeMillis().hashCode(),
    @SerializedName("task")
    val task: String,
    @SerializedName("selected")
    val selected: Boolean = false,
)
