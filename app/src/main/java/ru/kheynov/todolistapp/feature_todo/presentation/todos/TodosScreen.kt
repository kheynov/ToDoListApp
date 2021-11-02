package ru.kheynov.todolistapp.feature_todo.presentation.todos

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.presentation.todos.components.OrderSection
import ru.kheynov.todolistapp.feature_todo.presentation.todos.components.TodoItem
import ru.kheynov.todolistapp.ui.theme.ToDoListAppTheme

@ExperimentalAnimationApi
@Preview(name = "Todos Screen Dark", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Todos Screen Light", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun TodosScreenPreview() {
    ToDoListAppTheme {
        val todosList = listOf(
            Todo(
                "Lorem ipsum",
                true
            ),
            Todo(
                "dolor sit amet",
                false
            ),
            Todo(
                "consectetur adipiscing elit",
                true
            ),
            Todo(
                "Aenean ornare hendrerit mauris sed lobortis",
                false
            ),
        )
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 18.dp)) {
            items(todosList) { todo ->
                TodoItem(
                    todo = todo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    onChecked = {},
                    onDeleteClick = {}
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun TodosScreen(
//    navController: NavController,
        viewModel: TodoViewModel = hiltViewModel()
    ) {
        val state = viewModel.state.value
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

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
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your todo",
                        style = MaterialTheme.typography.h4
                    )
                    IconButton(onClick = {
                        viewModel.onEvent(TodosEvent.ToggleOrderSection)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sort"
                        )
                    }
                    AnimatedVisibility(
                        visible = state.isOrderSelectionVisible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        OrderSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            todoOrder = state.todoOrder
                        ) {
                            viewModel.onEvent(TodosEvent.Order(it))
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

                                    },
                                onDeleteClick = {
                                    viewModel.onEvent(TodosEvent.DeleteTodo(todo))
                                    scope.launch {
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Todo Deleted",
                                            actionLabel = "Undo"
                                        )
                                        if (result == SnackbarResult.ActionPerformed) {
                                            viewModel.onEvent(TodosEvent.RestoreTodo)
                                        }
                                    }
                                },
                                onChecked = {}
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}