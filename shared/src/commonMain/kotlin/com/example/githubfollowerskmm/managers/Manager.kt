package com.example.githubfollowerskmm.managers

import com.example.githubfollowerskmm.DatabaseDriverFactory

object Manager {
    val NETWORK_MANAGER = KTNetworkManager()
    val PERSISTENCE_MANAGER = KTPersistenceManager(DatabaseDriverFactory())
}
