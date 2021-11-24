package com.example.githubfollowerskmm.models
import kotlinx.serialization.*

@Serializable
class Follower(
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String
)
