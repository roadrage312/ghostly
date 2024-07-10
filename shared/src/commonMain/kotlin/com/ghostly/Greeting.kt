package com.ghostly

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Get Ghosted, ${platform.name}!"
    }
}