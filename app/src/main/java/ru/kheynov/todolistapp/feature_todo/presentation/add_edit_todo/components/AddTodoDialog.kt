package ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kheynov.todolistapp.ui.theme.ToDoListAppTheme
import ru.kheynov.todolistapp.ui.theme.transparentBackground

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun AddTodoDialogPreview() {
    ToDoListAppTheme() {
        AddTodoDialog(
            onValueChange = {},
            textFieldLabel = "Enter todo name",
            onSave = {},
            onBack = {})
    }
}


@Composable
fun AddTodoDialog(
    modifier: Modifier = Modifier,
    text: String = "",
    textFieldLabel: String = "",
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    onSave: () -> Unit,
    onBack: () -> Unit,
//    onFocusChange: (FocusState) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(transparentBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(120.dp)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
            ) {
                TextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),

                    value = text,
                    onValueChange = onValueChange,
                    textStyle = textStyle,
                    label = { Text(text = textFieldLabel) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        cursorColor = MaterialTheme.colors.secondary,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    })
                )
                Row(
                    Modifier
                        .weight(0.9f)
                        .padding(horizontal = 8.dp)
                ) {
                    IconButton(
                        onClick = onBack, Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Save Button",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                    Spacer(modifier = Modifier.weight(6f))
                    IconButton(onClick = onSave, Modifier.weight(1f)) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Save Button",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                }

            }
        }
    }
}