package com.example.githubfollowerskmm.managers

import com.example.githubfollowerskmm.models.Follower
import com.example.githubfollowerskmm.models.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CancellationException

class KTNetworkManager {
    private val client = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    private val baseUrl = "https://api.github.com/users/"

    @Throws(Exception::class)
    suspend fun getFollowers(username: String, page: Int): List<Follower> {
        return client.get("$baseUrl$username/followers") {
            parameter("per_page", 100)
            parameter("page", page)
        }
    }

    @Throws(Exception::class)
    suspend fun getUserInfo(username: String): User {
        return client.get("$baseUrl$username")
    }
}
