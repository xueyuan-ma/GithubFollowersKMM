package com.example.githubfollowerskmm.managers

import com.example.githubfollowerskmm.models.Follower
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.CancellationException

class KTNetworkManager {
    private val client = HttpClient() {
        install(JsonFeature) {
            val format = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(format)
        }

        HttpResponseValidator {
            handleResponseException { exception ->
                throw CancellationException(exception.message, exception.cause)
            }
        }
    }

    private val baseUrl = "https://api.github.com/users/"

    suspend fun getFollowers(username: String, page: Int): List<Follower> {
        return client.get("$baseUrl$username/followers") {
            parameter("per_page", 100)
            parameter("page", page)
        }
    }
}
