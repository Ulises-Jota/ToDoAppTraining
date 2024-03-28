package com.byc.todoapptraining.addtasks.domain

import com.byc.todoapptraining.addtasks.data.TaskRepository
import com.byc.todoapptraining.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.add(taskModel)
    }
}
