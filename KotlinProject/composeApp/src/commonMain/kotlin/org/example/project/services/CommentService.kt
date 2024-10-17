package org.example.project.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.Comment

class CommentService {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchComments(): List<Comment> {
        val response = httpClient.get("https://us-central1-nr-software.cloudfunctions.net/myFunction/comments") {
            headers {
                append("Content-Type", "application/json")
            }
        }

        // Debug: print the raw response body
        val rawBody: String = response.bodyAsText()
        println("DEBUG Response Body:\n$rawBody")

        // Parse JSON response
        return Json.decodeFromString(rawBody)
    }
}
