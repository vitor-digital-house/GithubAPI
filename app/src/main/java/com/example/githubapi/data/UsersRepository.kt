package com.example.githubapi.data

import com.example.githubapi.data.dto.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository {

    private val api = usersApi

    suspend fun fetchUsers(): UsersResponse = withContext(Dispatchers.IO) {
        api.fetchUsers()
    }
}
