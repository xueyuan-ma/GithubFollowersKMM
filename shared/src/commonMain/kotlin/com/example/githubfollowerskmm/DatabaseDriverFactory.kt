package com.example.githubfollowerskmm

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    constructor()
    fun createDriver(): SqlDriver
}
