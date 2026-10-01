package com.example.talabatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val auth = FirebaseAuth.getInstance()

            // Start directly on the login screen for this flow
            var currentScreen by remember { mutableStateOf("login") }

            // Handle screen switching based on app state
            when (currentScreen) {
                "login" -> LoginScreen(
                    onLoginSuccess = { currentScreen = "profile" },
                    onNavigateToSignup = { currentScreen = "signup" }
                )
                "signup" -> SignupScreen(
                    onSignupSuccess = {
                        // After signing up successfully, sign them out
                        // so they are forced to log in explicitly!
                        auth.signOut()
                        currentScreen = "login"
                    },
                    onNavigateToLogin = { currentScreen = "login" }
                )
                "profile" -> ProfileScreen(
                    onLogout = {
                        auth.signOut()
                        currentScreen = "login"
                    }
                )
            }
        }
    }
}