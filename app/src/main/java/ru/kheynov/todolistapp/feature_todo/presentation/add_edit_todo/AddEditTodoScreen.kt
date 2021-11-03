package ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import ru.kheynov.todolistapp.R
import ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo.components.AddTodoDialog

@Composable
fun AddEditTotoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val titleState = viewModel.todoTitle.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditTodoViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTodoViewModel.UiEvent.SaveTodo -> {
                    navController.navigateUp()
                }
            }
        }
    }
    AddTodoDialog(
        onValueChange = {
            viewModel.onEvent(AddEditTodoEvent.EnteredTitle(it))
        },
        onSave = { viewModel.onEvent(AddEditTodoEvent.SaveTodo) },
        text = titleState.text,
        textStyle = MaterialTheme.typography.body1,
        textFieldLabel = stringResource(id = R.string.input_field_create_todo_label),
        onBack = {
            navController.navigateUp()
        }
    )
}