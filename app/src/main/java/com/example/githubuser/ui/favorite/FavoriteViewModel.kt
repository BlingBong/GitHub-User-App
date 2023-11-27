package com.example.githubuser.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubuser.data.local.FavoriteDB
import com.example.githubuser.data.local.FavoriteDao
import com.example.githubuser.model.UsersItem

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var favoriteDao: FavoriteDao?
    private var favoriteDB: FavoriteDB?

    init {
        favoriteDB = FavoriteDB.getDatabase(application)
        favoriteDao = favoriteDB?.favoriteDao()
    }

    fun getFavoriteList(): LiveData<List<UsersItem>>? {
        return favoriteDao?.getFavoriteList()
    }
}