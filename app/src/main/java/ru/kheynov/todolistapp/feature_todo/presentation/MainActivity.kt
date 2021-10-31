package ru.kheynov.todolistapp.feature_todo.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kheynov.todolistapp.ui.theme.ToDoListAppTheme
import ru.kheynov.todolistapp.ui.theme.colorAccent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ToDoElement()
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "DARK THEME")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, name = "LIGHT THEME")
@Composable
fun PreviewToDoElement() {
    ToDoListAppTheme() {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.size(300.dp, 200.dp)) {
            ToDoElement()
        }
    }
}

@Composable
fun ToDoElement() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(20.dp),
        elevation = 5.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hello Card")
        }
    }
}

