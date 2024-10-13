package com.example.tasksmanager

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    taskViewModel: TaskViewModel,
    onSaveClick: () -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var taskNote by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var isAdding by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create Task") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                TextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Task Name") }
                )
                TextField(
                    value = taskNote,
                    onValueChange = { taskNote = it },
                    label = { Text("Task Note") }
                )
                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Avoid double add if clicked multiple times
                        if (!isAdding) {
                            isAdding = true // Set the flag to prevent double-clicks

                            // Get the current max ID and increment it by 1
                            val maxId = taskViewModel.tasks.value.maxByOrNull { it.id }?.id ?: 0
                            val newTaskId = maxId + 1

                            val newTask = Task(
                                id = newTaskId, // Incremented ID
                                name = taskName,
                                note = taskNote,
                                dueDate = dueDate,
                                isComplete = false
                            )
                            taskViewModel.addTask(newTask)
                            Log.d("CreateTaskScreen", "Task added: $newTask")
                            isAdding = false // Reset the flag
                            onSaveClick()
                        }
                    }
                ) {
                    Text("Save")
                }
            }
        }
    )
}