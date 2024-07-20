package com.ghostly.datastore

import android.content.Context
import androidx.datastore.dataStore
import com.ghostly.login.models.LoginDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class LoginDetailsStoreImpl(
    private val context: Context,
) : LoginDetailsStore {

    private val Context.datastore by dataStore(
        fileName = DataStoreConstants.LOGIN_FILE_NAME,
        serializer = LoginDetailsSerializer()
    )
    override val loginDetails: Flow<LoginDetails?>
        get() = context.datastore.data

    override suspend fun get(): LoginDetails? = runBlocking {
        context.datastore.data.firstOrNull()
    }

    override suspend fun put(loginDetails: LoginDetails) {
        context.datastore.updateData { loginDetails }
    }

    override suspend fun delete() {
        context.datastore.updateData { LoginDetails() }
    }
}