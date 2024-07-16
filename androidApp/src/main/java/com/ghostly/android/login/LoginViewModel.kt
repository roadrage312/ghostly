package com.ghostly.android.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostly.android.login.data.GetSiteDetailsUseCase
import com.ghostly.android.login.models.LoginDetails
import com.ghostly.android.login.models.LoginDetailsStore
import com.ghostly.android.login.models.LoginState
import com.ghostly.android.login.models.SiteDetails
import com.ghostly.android.network.models.Result
import com.ghostly.android.posts.data.GetPostsUseCase
import com.ghostly.android.utils.isValidEmail
import com.ghostly.android.utils.isValidGhostDomain
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginDetailsStore: LoginDetailsStore,
    private val getSiteDetailsUseCase: GetSiteDetailsUseCase,
    private val getPostsUseCase: GetPostsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginState>(LoginState.CheckLogin)
    val uiState: StateFlow<LoginState> = _uiState

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _domain = MutableStateFlow("")
    val domain: StateFlow<String> = _domain

    private val _inputValidated = MutableStateFlow(false)
    val inputValidated: StateFlow<Boolean> = _inputValidated

    private val _validDomain = MutableStateFlow(false)
    val validDomain: StateFlow<Boolean> = _validDomain

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _siteDetails = MutableStateFlow<SiteDetails?>(null)
    val siteDetails: StateFlow<SiteDetails?> = _siteDetails

    init {
        viewModelScope.launch {
            val log = loginDetailsStore.get()?.isLoggedIn
            println("Goku: login: $log")
            _isLoggedIn.value = loginDetailsStore.get()?.isLoggedIn == true
            delay(1000)
            val log1 = loginDetailsStore.get()?.isLoggedIn
            println("Goku: login: $log1")

            if (_isLoggedIn.value.not()) {
                _uiState.emit(LoginState.Start)
            }
        }
    }

    private fun validateInput() {
        _inputValidated.value =
            (isValidEmail(_email.value) && _password.value.isEmpty().not())
                    || (_token.value.isEmpty().not() && isValidGhostDomain(_domain.value))
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

    fun onDomainChange(newDomain: String) {
        _domain.value = newDomain
        _validDomain.value = isValidGhostDomain(_domain.value)
        validateInput()
    }

    fun tryLogin() {
        viewModelScope.launch {
            loginDetailsStore.put(
                LoginDetails(
                    domainUrl = domain.value,
                    apiKey = token.value,
                    isLoggedIn = true
                )
            )

            val log = loginDetailsStore.get()?.isLoggedIn
            println("Goku: login store: $log")

            when (val result = getPostsUseCase.getOnePost()) {
                is Result.Success -> {
                    _isLoggedIn.value = true
                }

                is Result.Error -> {
                    loginDetailsStore.delete()
                    _inputValidated.value = false
                    _uiState.value =
                        LoginState.InvalidApiKey("${result.errorCode}: ${result.message}")
                    _isLoggedIn.value = false
                }
            }
        }
    }

    suspend fun getSiteDetails() {
        when (val result = getSiteDetailsUseCase.invoke(domain.value)) {
            is Result.Success -> {
                _siteDetails.value = result.data?.siteDetails
                _uiState.value = LoginState.ValidDomain
            }

            is Result.Error -> {
                _validDomain.value = false
                _uiState.value = LoginState.InvalidDomain("${result.errorCode}: ${result.message}")
            }
        }
    }

    fun notYouClicked() {
        reset()
    }

    private fun reset() {
        _siteDetails.value = null
        _uiState.value = LoginState.Start
        _domain.value = ""
        _token.value = ""
        _email.value = ""
        _password.value = ""
        _inputValidated.value = false
        _validDomain.value = false
        _isLoggedIn.value = false
    }
}
