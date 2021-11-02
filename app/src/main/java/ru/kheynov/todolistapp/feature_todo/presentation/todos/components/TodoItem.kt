package ru.kheynov.todolistapp.feature_todo.presentation.todos.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.ui.theme.ToDoListAppTheme

@Preview(name = "Light", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TodoItemPreview() {
    ToDoListAppTheme {
        TodoItem(
            todo = Todo(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                true,
                1312414
            ),
            onDeleteClick = {},
            onChecked = {}
        )
    }
}


@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit,
    onChecked: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .height(70.dp)
            .padding(horizontal = 18.dp),
        shape = RoundedCornerShape(cornerRadius),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(18.dp))
            Checkbox(
                checked = todo.isChecked,
                onCheckedChange = onChecked
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(16f)
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