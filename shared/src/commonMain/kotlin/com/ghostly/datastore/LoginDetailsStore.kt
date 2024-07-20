package com.ghostly.datastore

import com.ghostly.login.models.LoginDetails
import kotlinx.coroutines.flow.Flow

interface LoginDetailsStore {

    val loginDetails: Flow<LoginDetails?>

    suspend fun get(): LoginDetails?

    suspend fun put(loginDetails: LoginDetails)

    suspend fun delete()
}