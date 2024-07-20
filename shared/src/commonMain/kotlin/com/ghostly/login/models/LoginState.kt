package com.ghostly.login.models

sealed class LoginState {
    data object CheckLogin : LoginState()
    data object Start : LoginState()
    data class InvalidDomain(val message: String) : LoginState()
    data object ValidDomain : LoginState()
    data class InvalidApiKey(val message: String) : LoginState()
}
