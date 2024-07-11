package com.ghostly.android.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostly.android.utils.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _inputValidated = MutableStateFlow(false)
    val inputValidated: StateFlow<Boolean> = _inputValidated

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private fun validateInput() {
        _inputValidated.value =
            (isValidEmail(_email.value) && _password.value.isEmpty().not())
                    || _token.value.isEmpty().not()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        validateInput()
    }

    fun onPasswordChange(newPass: String) {
        _password.value = newPass
        validateInput()
    }

    fun onTokenChange(newToken: String) {
        _token.value = newToken
        validateInput()
    }

    fun tryLogin() {
        viewModelScope.launch {
            _isLoggedIn.value = true // Simulate successful login
        }
    }
}
