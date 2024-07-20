package com.ghostly.network

import com.ghostly.datastore.LoginDetailsStore
import com.ghostly.network.models.Token
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.WeakKeyException
import java.time.Duration
import java.time.Instant
import java.util.Date

class TokenProviderImpl(
    private val loginDetailsStore: LoginDetailsStore,
) : TokenProvider {
    override suspend fun generateToken(): Token? {
        val loginDetails = loginDetailsStore.get() ?: return null

        val apiKey = loginDetails.apiKey ?: return null

        val (id, secret) = apiKey.split(':')

        val iat = Instant.now()
        val header = mapOf(
            "alg" to "HS256",
            "typ" to "JWT",
            "kid" to id
        )
        try {
            val timeToAdd = Duration.ofMinutes(5)

            val secretBytes = secret.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
            val key = Keys.hmacShaKeyFor(secretBytes)

            val token = Jwts.builder()
                .header().add(header).and()
                .claims()
                .audience().add("/admin/").and()
                .issuedAt(Date.from(iat))
                .expiration(Date.from(iat.plus(timeToAdd)))
                .and()
                .signWith(key, Jwts.SIG.HS256)
                .compact()

            return Token(token)
        } catch (e: WeakKeyException) {
            //Todo Log exception
            return null
        }
    }
}