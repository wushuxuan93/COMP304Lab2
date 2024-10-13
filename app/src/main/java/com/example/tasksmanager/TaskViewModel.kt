package com.example.tasksmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.getTasks().collect { loadedTasks ->
                _tasks.value = loadedTasks
            }
        }
    }

    fun addTask(task: Task) {
        repository.addTask(task)
        _tasks.update { currentTasks ->2
            (currentTasks + task).distinctBy { it.id }
        }
    }

    fun updateTask(updatedTask: Task) {
        repository.updateTask(updatedTask)
        _tasks.update { currentTasks ->
            currentTasks.map { if (it.id == updatedTask.id) updatedTask else it }
        }
    }
}