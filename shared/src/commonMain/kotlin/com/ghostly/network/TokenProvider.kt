package com.ghostly.network

import com.ghostly.network.models.Token

interface TokenProvider {
    suspend fun generateToken(): Token?
}