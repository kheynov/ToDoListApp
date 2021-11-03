package ru.kheynov.todolistapp.feature_todo.presentation.todos

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.presentation.todos.components.OrderSection
import ru.kheynov.todolistapp.feature_todo.presentation.todos.components.TodoItem
import ru.kheynov.todolistapp.feature_todo.presentation.util.ScreenRoutes


@ExperimentalAnimationApi
@Composable
fun TodosScreen(
    navController: NavController,
    viewModel: TodoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ScreenRoutes.AddEditTodoScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(2f))
                Text(
                    modifier = Modifier.weight(5f),
                    text = "ToDo's",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                    viewModel.onEvent(TodosEvent.ToggleOrderSection)
                }) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSelectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Row {
                    Spacer(Modifier.width(16.dp))

                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        todoOrder = state.todoOrder
                    ) {
                        viewModel.onEvent(TodosEvent.Order(it))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.todos) { todo ->
                    TodoItem(
                        todo = todo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    ScreenRoutes.AddEditTodoScreen.route +
                                            "?todoId=${todo.id}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(TodosEvent.DeleteTodo(todo))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Todo Deleted",
                                    actionLabel = "Undo",

                                    )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(TodosEvent.RestoreTodo)
                                }
                            }
                        },
                        onChecked = {
                            viewModel.onEvent(
                                TodosEvent.SaveTodo(
                                    Todo(
                                        title = todo.title,
                                        isChecked = it,
                                        id = todo.id,
                                        timestamp = System.currentTimeMillis(),
                                    )
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
