package com.example.githubuser.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.ApiConfig
import com.example.githubuser.model.UsersItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val listFollowingUser = MutableLiveData<ArrayList<UsersItem>>()

    fun setFollowingUser(username: String) {
        ApiConfig.getApiService()
            .getUserFollowing(username)
            .enqueue(object: Callback<ArrayList<UsersItem>> {
                override fun onResponse(
                    call: Call<ArrayList<UsersItem>>,
                    response: Response<ArrayList<UsersItem>>
                ) {
                    if (response.isSuccessful) {
                        listFollowingUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UsersItem>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getFollowingUser(): LiveData<ArrayList<UsersItem>> {
        return listFollowingUser
    }
}