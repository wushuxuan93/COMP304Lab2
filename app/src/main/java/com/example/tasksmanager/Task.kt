package com.example.tasksmanager

data class Task(
    val id: Int,
    val name: String,
    val note: String,
    val dueDate: String,
    val isComplete: Boolean
)
