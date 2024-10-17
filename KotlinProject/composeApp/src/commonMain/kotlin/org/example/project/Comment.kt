package org.example.project

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("_id")
    val id: String,
    val name: String,
    val email: String,
    @SerialName("movie_id")
    val movieId: String,
    val text: String,
    val date: String
)