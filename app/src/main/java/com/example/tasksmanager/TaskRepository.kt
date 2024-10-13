package com.example.tasksmanager

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepository {
    private var tasks = mutableListOf(
        Task(1, "Finish the assignment", "Mobile Apps Development Lab 2", "2024-10-13", false),
        Task(2, "Go grocery Store", "milk, egg, broccoli", "2024-10-06", false)
    )

    // Keep track of the next available ID
    private var nextId = tasks.maxOfOrNull { it.id }?.plus(1) ?: 1

    fun getTasks(): Flow<List<Task>> {
        return flow {
            emit(tasks)
        }
    }

    fun addTask(task: Task) {
        val newTask = task.copy(id = nextId++) // Ensure each task gets a unique ID
        tasks.add(newTask)
    }

    fun updateTask(updatedTask: Task) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }
}