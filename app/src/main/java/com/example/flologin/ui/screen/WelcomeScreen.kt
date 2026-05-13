package com.example.flologin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PurplePrimary = Color(0xFF6A1B9A)
private val PurpleLight = Color(0xFFEDE7F6)

@Composable
fun WelcomeScreen(
    username: String,
    onLogout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PurpleLight, Color.White))),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar circle
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(PurplePrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Text("Selamat Datang!", fontSize = 18.sp, color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Text(
                username,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = PurplePrimary
            )

            Spacer(Modifier.height(8.dp))
            Text(
                "Anda berhasil masuk ke aplikasi 🎉",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(48.dp))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = PurplePrimary)
            ) {
                Icon(Icons.Filled.ExitToApp, null)
                Spacer(Modifier.width(8.dp))
                Text("LOGOUT", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}