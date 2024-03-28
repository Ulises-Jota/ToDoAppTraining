package com.byc.todoapptraining.addtasks.ui

import com.byc.todoapptraining.addtasks.ui.model.TaskModel

sealed interface TasksUiState {
    data object Loading: TasksUiState
    data class Error(val throwable: Throwable): TasksUiState
    data class Success(val tasks: List<TaskModel>): TasksUiState
}
