package ru.kheynov.todolistapp.feature_todo.presentation.todos.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo

@Preview
@Composable
fun TodoItemPreview() {
    TodoItem(todo = Todo("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", false, 1312414)) {

    }
}


@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier.height(70.dp),
        shape = RoundedCornerShape(cornerRadius),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
//                .padding(16.dp)
//                .padding(end = 32.dp),
            ) {
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(
                checked = todo.isChecked, onCheckedChange = {}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(16f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.weight(2f)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Todo")
            }
        }
    }
}