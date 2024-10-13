package com.example.tasksmanager

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TaskNavGraph(
    taskViewModel: TaskViewModel,
    windowSizeClass: WindowSizeClass
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(
                taskViewModel = taskViewModel,
                windowSizeClass = windowSizeClass, // Pass windowSizeClass to HomeView
                onTaskClick = { taskId ->
                    navController.navigate("edit_task/$taskId")
                },
                onAddTaskClick = {
                    navController.navigate("create_task")
                }
            )
        }
        composable("create_task") {
            CreateTaskScreen(
                taskViewModel = taskViewModel,
                onSaveClick = { navController.popBackStack() }
            )
        }
        composable("edit_task/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toInt()
            taskId?.let {
                EditTaskScreen(
                    taskViewModel = taskViewModel,
                    taskId = it,
                    onSaveClick = { navController.popBackStack() }
                )
            }
        }
    }
}

