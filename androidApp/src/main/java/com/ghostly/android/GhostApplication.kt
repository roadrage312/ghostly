package com.ghostly.android

import android.app.Application
import com.ghostly.android.login.loginModule
import com.ghostly.android.network.networkModule
import com.ghostly.android.posts.postsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GhostApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GhostApplication)
            modules(appModule, loginModule, networkModule, postsModule)
        }
    }
}