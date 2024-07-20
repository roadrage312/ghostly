package com.ghostly.datastore

import androidx.datastore.core.Serializer
import com.ghostly.login.models.LoginDetails
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class LoginDetailsSerializer : Serializer<LoginDetails> {
    override val defaultValue: LoginDetails
        get() = LoginDetails()

    private val json = Json { encodeDefaults = true }

    override suspend fun readFrom(input: InputStream): LoginDetails {
        val decryptedBytes = input.use { it.readBytes() }
        val value = decryptedBytes.toString(charset("UTF-8"))
        return json.decodeFromString(LoginDetails.serializer(), value)
    }

    override suspend fun writeTo(details: LoginDetails, output: OutputStream) {
        val value = json.encodeToString(LoginDetails.serializer(), details)
        output.use {
            it.write(value.encodeToByteArray())
        }
    }
}