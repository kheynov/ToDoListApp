package ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo

import androidx.compose.ui.focus.FocusState

sealed class AddEditTotoEvent {
    data class EnteredTitle(val value: String) : AddEditTotoEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditTotoEvent()
    object SaveTodo : AddEditTotoEvent()
}
