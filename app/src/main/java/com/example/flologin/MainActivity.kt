package com.example.flologin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flologin.data.local.database.AppDatabase
import com.example.flologin.data.repository.UserRepository
import com.example.flologin.ui.screen.LoginScreen
import com.example.flologin.ui.screen.RegisterScreen
import com.example.flologin.ui.screen.WelcomeScreen
import com.example.flologin.viewmodel.LoginViewModel
import com.example.flologin.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        val factory = LoginViewModelFactory(repository)

        setContent {
            val viewModel: LoginViewModel = viewModel(factory = factory)

            LaunchedEffect(Unit) {
                viewModel.insertDummyUser()
            }

            // Navigation sederhana pakai state
            var currentScreen by remember { mutableStateOf("login") }
            val loggedInUser = viewModel.loggedInUser

            // Auto navigate ke welcome saat login berhasil
            LaunchedEffect(loggedInUser) {
                if (loggedInUser != null) {
                    currentScreen = "welcome"
                }
            }

            when {
                loggedInUser != null -> {
                    WelcomeScreen(
                        username = loggedInUser,
                        onLogout = {
                            viewModel.logout()
                            currentScreen = "login"
                        }
                    )
                }
                currentScreen == "register" -> {
                    RegisterScreen(
                        viewModel = viewModel,
                        onNavigateToLogin = {
                            viewModel.resetRegisterState()
                            currentScreen = "login"
                        }
                    )
                }
                else -> {
                    LoginScreen(
                        viewModel = viewModel,
                        onNavigateToRegister = {
                            currentScreen = "register"
                        }
                    )
                }
            }
        }
    }
}