package com.byc.todoapptraining.addtasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byc.todoapptraining.addtasks.domain.AddTaskUseCase
import com.byc.todoapptraining.addtasks.domain.DeleteTaskUseCase
import com.byc.todoapptraining.addtasks.domain.GetTasksUseCase
import com.byc.todoapptraining.addtasks.domain.UpdateTaskUseCase
import com.byc.todoapptraining.addtasks.ui.TasksUiState.Error
import com.byc.todoapptraining.addtasks.ui.TasksUiState.Loading
import com.byc.todoapptraining.addtasks.ui.TasksUiState.Success
import com.byc.todoapptraining.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> =
        getTasksUseCase().map(::Success)
            .catch {
                Error(it)
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        onDialogClose()

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(
                taskModel.copy(
                    selected = !taskModel.selected
                )
            )
        }
    }

    fun onItemRemoved(taskModel: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }
    }
}
