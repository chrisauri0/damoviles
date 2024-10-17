package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.project.CommentRepository
import org.example.project.services.CommentViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        // Crear o inyectar el repositorio
        val repository = remember { CommentRepository() }
        // Crear el ViewModel con el repositorio
        val viewModel = remember { CommentViewModel(repository) }

        // Cargar comentarios cuando la pantalla se muestra
        LaunchedEffect(Unit) {
            viewModel.loadComments()
        }

        val comments by viewModel.comments.collectAsState()

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(comments) { comment ->
                CommentRow(comment = comment)
            }
        }
    }
}

// Composable para mostrar cada comentario
@Composable
fun CommentRow(comment: Comment) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Name: ${comment.name}")
        Text(text = "Email: ${comment.email}")
        Text(text = "Message: ${comment.text}")
        Text(text = "Date: ${comment.date}")
    }
}
