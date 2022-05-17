package com.example.githubapi.data

import com.example.githubapi.data.dto.UsersResponse
import retrofit2.http.GET

interface UsersApi {

    @GET("search/users?q=language:java+location:saopaulo")
    suspend fun fetchUsers(): UsersResponse
}