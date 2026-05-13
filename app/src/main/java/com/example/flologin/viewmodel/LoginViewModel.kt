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

    var registerState by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    // Username yang sedang login (untuk welcome screen)
    var loggedInUser by mutableStateOf<String?>(null)
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            loginState = ""
            delay(600)
            val user = repository.login(username, password)
            if (user != null) {
                loggedInUser = user.username
                loginState = "Login Berhasil"
            } else {
                loginState = "Username atau Password Salah"
            }
            isLoading = false
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            registerState = ""
            delay(600)

            if (repository.isUsernameTaken(username)) {
                registerState = "Username sudah dipakai"
            } else {
                val result = repository.insert(
                    User(username = username, password = password)
                )
                registerState = if (result != -1L) {
                    "Pendaftaran Berhasil! Silakan login."
                } else {
                    "Pendaftaran Gagal"
                }
            }
            isLoading = false
        }
    }

    fun logout() {
        loggedInUser = null
        loginState = ""
    }

    fun resetRegisterState() {
        registerState = ""
    }

    fun insertDummyUser() {
        viewModelScope.launch {
            repository.insert(
                User(username = "admin", password = "12345")
            )
        }
    }
}