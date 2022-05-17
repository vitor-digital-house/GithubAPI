package com.example.githubapi.ui

import com.example.githubapi.ui.vo.UserVO

sealed class Result

object Loading : Result()

data class Success(
    val data: List<UserVO>
) : Result()

object Error : Result() {
    val genericMsg: String = "Ops, algo deu errado!"
}