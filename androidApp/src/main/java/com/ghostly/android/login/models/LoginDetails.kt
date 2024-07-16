package com.ghostly.android.login.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginDetails(
    val authMode: Int = 1, // 0 or 1(Email or API Key)
    val domainUrl: String? = null,
    val email: String? = null,
    val password: String? = null,
    val apiKey: String? = null,
    val isLoggedIn: Boolean = false,
)