package ru.kheynov.todolistapp.feature_todo.presentation.todos.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kheynov.todolistapp.R
import ru.kheynov.todolistapp.feature_todo.domain.util.OrderType
import ru.kheynov.todolistapp.feature_todo.domain.util.TodoOrder
import ru.kheynov.todolistapp.ui.theme.ToDoListAppTheme

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "light")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "dark")
@Composable
fun OrderSectionPreview() {
    ToDoListAppTheme {
        OrderSection(onOrderChange = {})
    }
}

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    todoOrder: TodoOrder = TodoOrder.Title(OrderType.Descending),
    onOrderChange: (TodoOrder) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(36.dp))
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {

            DefaultRadioButton(
                text = stringResource(id = R.string.sortRadioButtonTitle),
                selected = todoOrder is TodoOrder.Title,
                onSelect = { onOrderChange(TodoOrder.Title(todoOrder.orderType)) }
            )
            Spacer(modifier = Modifier.height(18.dp))
            DefaultRadioButton(
                text = stringResource(id = R.string.sortRadioButtonDate),
                selected = todoOrder is TodoOrder.Date,
                onSelect = { onOrderChange(TodoOrder.Date(todoOrder.orderType)) }
            )
        }
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                IconRadioButton(
                    imageVector = Icons.Default.ArrowUpward,
                    selected = todoOrder.orderType is OrderType.Ascending,
                    onSelect = {
                        onOrderChange(todoOrder.copy(OrderType.Ascending))
                    })
                Spacer(modifier = Modifier.height(18.dp))
                IconRadioButton(
                    imageVector = Icons.Default.ArrowDownward,
                    selected = todoOrder.orderType is OrderType.Descending,
                    onSelect = {
                        onOrderChange(todoOrder.copy(OrderType.Descending))
                    })
            }
        }
    }
}
