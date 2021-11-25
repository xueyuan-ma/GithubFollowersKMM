package com.example.githubfollowerskmm.models
import comexampledb.DBFollower
import kotlinx.serialization.*

@Serializable
data class Follower(
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String
)
