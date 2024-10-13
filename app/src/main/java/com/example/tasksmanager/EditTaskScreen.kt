package com.example.tasksmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskViewModel: TaskViewModel,
    taskId: Int,
    onSaveClick: () -> Unit
) {
    val task = taskViewModel.tasks.collectAsState().value.find { it.id == taskId }

    if (task == null) {
        Text("Task not found")
        return
    }

    var taskName by remember { mutableStateOf(task.name) }
    var taskNote by remember { mutableStateOf(task.note) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var isComplete by remember { mutableStateOf(task.isComplete) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit Task") })
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isComplete,
                        onCheckedChange = { isComplete = it }
                    )
                    Text(text = "Completed")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val updatedTask = task.copy(
                        name = taskName,
                        note = taskNote,
                        dueDate = dueDate,
                        isComplete = isComplete
                    )
                    taskViewModel.updateTask(updatedTask)
                    onSaveClick() // Navigate back
                }) {
                    Text("Save")
                }
            }
        }
    )
}