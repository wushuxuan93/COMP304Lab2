package com.example.tasksmanager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit

@Composable
fun HomeView(
    taskViewModel: TaskViewModel,
    windowSizeClass: WindowSizeClass,
    onTaskClick: (Int) -> Unit,
    onAddTaskClick: () -> Unit
) {
    val tasks by taskViewModel.tasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        modifier = Modifier.semantics {
            contentDescription = "Task list screen with ${tasks.size} tasks"
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = tasks,
                key = { it.id }
            ) { task ->
                TaskItem(
                    task = task,
                    fontSize = 14.sp, // example value
                    padding = PaddingValues(16.dp),
                    onClick = { onTaskClick(task.id) }
                )
            }
        }
    }
}



@Composable
fun TaskItem(
    task: Task,
    fontSize: TextUnit,
    padding: PaddingValues,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .semantics { // Add semantics modifier for accessibility
                contentDescription = "Task: ${task.name}, Due on: ${task.dueDate}, Status: ${if (task.isComplete) "Complete" else "Incomplete"}"
            }
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = task.name,
                fontSize = fontSize,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = task.note,
                fontSize = fontSize,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Due Date: ${task.dueDate}",
                fontSize = fontSize,
                style = MaterialTheme.typography.bodySmall
            )
            Checkbox(
                checked = task.isComplete,
                onCheckedChange = null
            )
        }
    }
}

