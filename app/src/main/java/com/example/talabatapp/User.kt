package com.example.talabatapp

// Firebase needs a no-argument constructor to deserialize data,
// which is why every property has a default value here.
data class User(
    val name: String = "",
    val email: String = "",
    val address: String = "",
    val phone: String = ""
)