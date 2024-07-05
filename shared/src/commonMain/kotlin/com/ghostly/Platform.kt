package com.ghostly

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform