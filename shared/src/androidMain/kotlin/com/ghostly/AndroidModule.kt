package com.ghostly

import androidx.room.Room
import com.ghostly.database.GhostlyDatabase
import com.ghostly.datastore.DataStoreRepository
import com.ghostly.datastore.DataStoreRepositoryImpl
import com.ghostly.network.networkModule
import org.koin.dsl.module

private val androidModule = module {
    single {
        Room.databaseBuilder(
            get(),
            GhostlyDatabase::class.java, "ghostly.db"
        ).build()
    }

    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}

val androidModules = listOf(networkModule, androidModule)