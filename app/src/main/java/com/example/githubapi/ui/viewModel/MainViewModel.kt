package com.example.githubapi.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapi.data.UsersRepository
import com.example.githubapi.ui.Error
import com.example.githubapi.ui.Loading
import com.example.githubapi.ui.Result
import com.example.githubapi.ui.Success
import com.example.githubapi.ui.vo.UserVO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    private val repository: UsersRepository = UsersRepository()

    private val _users: MutableLiveData<Result> = MutableLiveData()
    val users: LiveData<Result> = _users

    fun fetchUsers() {
        viewModelScope.launch {
            _users.value = Loading

            try {
                val response = repository.fetchUsers()
                val vo = response.items.map { userDTO ->
                    UserVO(
                        name = userDTO.login,
                        link = userDTO.url,
                        identifier = userDTO.id,
                    )
                }
                _users.value = Success(vo)
            } catch (ex: HttpException) {
                _users.value = Error
            }
        }
    }
}