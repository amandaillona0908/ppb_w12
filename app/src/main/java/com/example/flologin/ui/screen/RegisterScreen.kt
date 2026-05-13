package com.example.flologin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flologin.viewmodel.LoginViewModel

private val PurplePrimary = Color(0xFF6A1B9A)
private val PurpleLight = Color(0xFFEDE7F6)
private val SuccessGreen = Color(0xFF2E7D32)
private val ErrorRed = Color(0xFFC62828)

@Composable
fun RegisterScreen(
    viewModel: LoginViewModel,
    onNavigateToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading
    val registerState = viewModel.registerState

    val passwordMatch = password == confirmPassword
    val isFormValid = username.length >= 3 &&
            password.length >= 5 &&
            passwordMatch

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PurpleLight, Color.White))),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Back button + title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigateToLogin, enabled = !isLoading) {
                        Icon(Icons.Filled.ArrowBack, "Kembali", tint = PurplePrimary)
                    }
                    Spacer(Modifier.weight(1f))
                    Text("Daftar Akun", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = PurplePrimary)
                    Spacer(Modifier.weight(1.2f))
                }

                Spacer(Modifier.height(8.dp))
                Text("Buat akun baru untuk login", fontSize = 13.sp, color = Color.Gray)
                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username (min. 3 karakter)") },
                    leadingIcon = { Icon(Icons.Filled.Person, null) },
                    singleLine = true,
                    isError = username.isNotEmpty() && username.length < 3,
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PurplePrimary,
                        focusedLabelColor = PurplePrimary,
                        focusedLeadingIconColor = PurplePrimary
                    )
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password (min. 5 karakter)") },
                    leadingIcon = { Icon(Icons.Filled.Lock, null) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility, null)
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    isError = password.isNotEmpty() && password.length < 5,
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PurplePrimary,
                        focusedLabelColor = PurplePrimary,
                        focusedLeadingIconColor = PurplePrimary
                    )
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Konfirmasi Password") },
                    leadingIcon = { Icon(Icons.Filled.Lock, null) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    isError = confirmPassword.isNotEmpty() && !passwordMatch,
                    supportingText = {
                        if (confirmPassword.isNotEmpty() && !passwordMatch) {
                            Text("Password tidak cocok", color = ErrorRed, fontSize = 12.sp)
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PurplePrimary,
                        focusedLabelColor = PurplePrimary,
                        focusedLeadingIconColor = PurplePrimary
                    )
                )

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.register(username, password) },
                    enabled = isFormValid && !isLoading,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PurplePrimary)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(Modifier.size(22.dp), color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text("DAFTAR", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                if (registerState.isNotEmpty()) {
                    Spacer(Modifier.height(16.dp))
                    val isSuccess = registerState.contains("Berhasil")
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSuccess) SuccessGreen.copy(alpha = 0.12f) else ErrorRed.copy(alpha = 0.12f)
                        )
                    ) {
                        Text(
                            registerState,
                            modifier = Modifier.padding(12.dp),
                            color = if (isSuccess) SuccessGreen else ErrorRed,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Sudah punya akun? ", fontSize = 13.sp, color = Color.Gray)
                    Text(
                        "Login di sini",
                        fontSize = 13.sp,
                        color = PurplePrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable(enabled = !isLoading) { onNavigateToLogin() }
                    )
                }
            }
        }
    }
}