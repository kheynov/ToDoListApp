package ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo

import androidx.compose.ui.focus.FocusState

sealed class AddEditTodoEvent {
    data class EnteredTitle(val value: String) : AddEditTodoEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditTodoEvent()
    object SaveTodo : AddEditTodoEvent()
}
