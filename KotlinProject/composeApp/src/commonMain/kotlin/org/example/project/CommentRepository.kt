package org.example.project

import org.example.project.services.CommentService

class CommentRepository(
    private val service: CommentService = CommentService()
) {
    suspend fun getComments(): List<Comment> {
        return service.fetchComments()
    }
}