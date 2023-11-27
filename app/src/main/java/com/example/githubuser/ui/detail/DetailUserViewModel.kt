package com.example.githubuser.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.data.remote.ApiConfig
import com.example.githubuser.data.local.FavoriteDB
import com.example.githubuser.data.local.FavoriteDao
import com.example.githubuser.model.UsersItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    val listDetailUsers = MutableLiveData<UsersItem>()

    private var favoriteDao: FavoriteDao?
    private var favoriteDB: FavoriteDB?

    init {
        favoriteDB = FavoriteDB.getDatabase(application)
        favoriteDao = favoriteDB?.favoriteDao()
    }

    fun setDetailUser(username: String) {
        ApiConfig.getApiService()
            .getUserDetail(username)
            .enqueue(object : Callback<UsersItem> {
                override fun onResponse(
                    call: Call<UsersItem>,
                    response: Response<UsersItem>
                ) {
                    if (response.isSuccessful) {
                        listDetailUsers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getDetailUser(): LiveData<UsersItem> {
        return listDetailUsers
    }

    fun insertToFavorite(user: UsersItem) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDao?.insertToFavorite(user)
        }
    }

    suspend fun getFavoriteDetail(username: String) = favoriteDao?.getFavoriteDetail(username)

    fun deleteFromFavorite(user: UsersItem) {
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDao?.deleteFromFavorite(user)
        }
    }
}