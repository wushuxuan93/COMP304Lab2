package com.example.tasksmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.tasksmanager.ui.theme.TasksManagerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = TaskRepository()
        val taskViewModel = TaskViewModel(repository)

        setContent {
            // Use calculateWindowSizeClass to determine the size of the screen
            val windowSizeClass = calculateWindowSizeClass(this)

            TasksManagerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TaskNavGraph(
                        taskViewModel = taskViewModel,
                        windowSizeClass = windowSizeClass
                    )
                }
            }
        }
    }
}

