package com.example.githubfollowerskmm.models

import kotlinx.serialization.*

@Serializable
data class User(
    val login: String,
    @SerialName("avatar_url")val avatarUrl: String,
    val name: String?,
    val location: String?,
    val bio: String?,
    @SerialName("public_repos")val publicRepos: Int,
    @SerialName("public_gists")val publicGists: Int,
    @SerialName("html_url")val htmlUrl: String,
    val following: Int,
    val followers: Int,
    @SerialName("created_at")val createdAt: String
)
