package ru.kheynov.todolistapp.feature_todo.presentation.todos.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo

@Preview
@Composable
fun TodoItemPreview() {
    TodoItem(
        todo = Todo(
            "Todo Example",
            false,
            23123123,
            12132,
        )
    )
}

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onChecked: (Boolean) -> Unit = {},
) {
    Card(
        modifier = modifier
            .height(70.dp),
        shape = RoundedCornerShape(cornerRadius),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onEditClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(56.dp)
                .clickable { onChecked(!todo.isChecked) },
                contentAlignment = Alignment.Center) {

                Checkbox(
                    checked = todo.isChecked,
                    onCheckedChange = onChecked
                )

            }
            Text(
                text = todo.title,
                style = MaterialTheme.typography.h5,
                textDecoration = if (todo.isChecked) TextDecoration.LineThrough else TextDecoration.None,
                color = if (todo.isChecked) Color.Gray else MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(16f)
                    .padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.weight(2f)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Todo",
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(18.dp))
        }
    }
}