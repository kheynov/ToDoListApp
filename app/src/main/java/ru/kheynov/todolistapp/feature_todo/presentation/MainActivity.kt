package ru.kheynov.todolistapp.feature_todo.presentation

import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo.AddEditTotoScreen
import ru.kheynov.todolistapp.feature_todo.presentation.todos.TodosScreen
import ru.kheynov.todolistapp.feature_todo.presentation.util.ScreenRoutes
import ru.kheynov.todolistapp.ui.theme.ToDoListAppTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ToDoListAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoutes.TodosScreen.route
                    ) {
                        composable(route = ScreenRoutes.TodosScreen.route) {
                            TodosScreen(navController = navController)
                        }
                        composable(
                            route = ScreenRoutes.AddEditTodoScreen.route +
                                    "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(name = "todoId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }),
                        ) {
                            AddEditTotoScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
