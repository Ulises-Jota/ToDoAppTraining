package com.byc.todoapptraining.addtasks.domain

import com.byc.todoapptraining.addtasks.data.TaskRepository
import com.byc.todoapptraining.addtasks.ui.model.TaskModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository.tasks
    }
}
