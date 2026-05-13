package com.example.flologin.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flologin.data.local.entity.User
import com.example.flologin.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var loginState by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            loginState = ""
            delay(800) // delay biar loading-nya keliatan (Room kecepetan)
            val user = repository.login(username, password)
            loginState = if (user != null) {
                "Login Berhasil"
            } else {
                "Username atau Password Salah"
            }
            isLoading = false
        }
    }

    fun insertDummyUser() {
        viewModelScope.launch {
            repository.insert(
                User(username = "admin", password = "12345")
            )
        }
    }
}