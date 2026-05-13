package com.example.flologin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flologin.data.local.database.AppDatabase
import com.example.flologin.data.repository.UserRepository
import com.example.flologin.ui.screen.LoginScreen
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

            LoginScreen(viewModel)
        }
    }
}