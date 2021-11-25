package com.example.githubfollowerskmm.managers

import com.example.db.AppDatabase
import com.example.githubfollowerskmm.DatabaseDriverFactory
import com.example.githubfollowerskmm.models.Follower

class KTPersistenceManager(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val databaseQueries = database.appDatabaseQueries

    fun retrieveFavorites(): List<Follower> {
        return databaseQueries.selectAllFollowers { login, avatarUrl -> Follower(login, avatarUrl) }.executeAsList()
    }

    fun insertFavorite(follower: Follower) {
        databaseQueries.insertFollower(follower.login, follower.avatarUrl)
    }

    fun deleteFavorite(follower: Follower) {
        databaseQueries.deleteFolloer(follower.login)
    }
}
