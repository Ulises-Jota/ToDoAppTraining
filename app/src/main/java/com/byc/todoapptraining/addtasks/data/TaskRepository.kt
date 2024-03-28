package com.byc.todoapptraining.addtasks.data

import com.byc.todoapptraining.addtasks.ui.model.TaskModel
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { tasksList ->
        tasksList.map { taskEntity ->
            TaskModel(
                id = taskEntity.id,
                task = taskEntity.task,
                selected = taskEntity.selected
            )
        }
    }

    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(
            taskModel.toTaskEntity()
        )
    }

    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(
            taskModel.toTaskEntity()
        )
    }

    suspend fun delete(taskModel: TaskModel) {
        taskDao.deleteTask(
            taskModel.toTaskEntity()
        )
    }
}

fun TaskModel.toTaskEntity() =
    TaskEntity(id, task, selected)
