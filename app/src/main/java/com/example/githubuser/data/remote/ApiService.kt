package com.example.githubuser.data.remote

import com.example.githubuser.model.GithubResponse
import com.example.githubuser.model.UsersItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUser (
        @Query("q")
        query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getUserDetail (
        @Path("username")
        username: String
    ): Call<UsersItem>

    @GET("users/{username}/followers")
    fun getUserFollowers (
        @Path("username")
        username: String
    ): Call<ArrayList<UsersItem>>

    @GET("users/{username}/following")
    fun getUserFollowing (
        @Path("username")
        username: String
    ): Call<ArrayList<UsersItem>>
}