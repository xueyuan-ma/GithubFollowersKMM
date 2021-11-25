package com.example.githubfollowerskmm

import com.example.githubfollowerskmm.managers.KTNetworkManager
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}
