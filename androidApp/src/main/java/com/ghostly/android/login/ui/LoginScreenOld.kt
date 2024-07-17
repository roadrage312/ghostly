package com.ghostly.android.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ghostly.android.R
import com.ghostly.android.login.LoginViewModel
import com.ghostly.android.ui.components.AccentButton
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun LoginScreenOld(
    loginViewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit
) {
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val token by loginViewModel.token.collectAsState()
    val domain by loginViewModel.domain.collectAsState()
    val inputValidated by loginViewModel.inputValidated.collectAsState()
    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            onLoginSuccess()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.welcome_to),
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.ghost),
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = stringResource(R.string.enter_email_password),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = loginViewModel::onEmailChange,
                label = { Text(stringResource(R.string.email)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = loginViewModel::onPasswordChange,
                label = { Text(stringResource(R.string.password)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    loginViewModel.tryLogin()
                })
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 15.dp),
                    text = stringResource(R.string.or),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.domain_api_key),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = domain,
                onValueChange = loginViewModel::onDomainChange,
                label = { Text(stringResource(R.string.ghost_domain)) },
                placeholder = { Text(text = stringResource(R.string.cd_domain_placeholder)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = token,
                onValueChange = loginViewModel::onTokenChange,
                label = { Text(stringResource(R.string.admin_key)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    loginViewModel.tryLogin()
                }),
            )
            Spacer(modifier = Modifier.height(24.dp))
            AccentButton(
                onClick = { loginViewModel.tryLogin() },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = inputValidated
            ) {
                Text(
                    text = stringResource(R.string.login).uppercase(Locale.getDefault()),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
