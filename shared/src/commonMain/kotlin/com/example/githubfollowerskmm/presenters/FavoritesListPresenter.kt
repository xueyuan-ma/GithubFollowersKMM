package com.example.githubfollowerskmm.presenters

import com.example.githubfollowerskmm.managers.Manager
import com.example.githubfollowerskmm.models.Follower
import kotlinx.coroutines.*

class FavoritesListPresenter<T: FavoritesListViewInterface>(view: T) : BasePresenter<T>(view) {
    fun getFavorites() {
        scope.launch {
            val favorites = Manager.PERSISTENCE_MANAGER.retrieveFavorites()
            withContext(Dispatchers.Main) {
                if(favorites.isEmpty()) {
                    view?.showEmptyState()
                } else {
                    view?.showFavorites(favorites)
                }
            }
        }
    }
}

interface FavoritesListViewInterface {
    fun showEmptyState()
    fun showFavorites(favorites: List<Follower>)
}
