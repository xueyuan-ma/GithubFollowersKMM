package com.example.githubfollowerskmm.presenters

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BasePresenter<T>(protected val view: T?) {
    protected val scope = CoroutineScope(Job() + Dispatchers.Default)
}
