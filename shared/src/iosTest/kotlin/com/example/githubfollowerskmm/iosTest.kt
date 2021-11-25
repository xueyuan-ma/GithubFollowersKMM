package com.example.githubfollowerskmm

import com.example.githubfollowerskmm.managers.KTNetworkManager
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("iOS"), "Check iOS is mentioned")
    }

    @Test
    fun testGetFollowers() = runBlocking {
        val networkManager = KTNetworkManager()
        val followers = networkManager.getFollowers("machinemxy", 1)
        print(followers.size)
        assertTrue(followers.isNotEmpty())
    }
}
