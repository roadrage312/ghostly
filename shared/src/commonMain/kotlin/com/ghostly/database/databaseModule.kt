package com.ghostly.database

import org.koin.dsl.module

val daoModule = module {
    single { get<GhostlyDatabase>().postDao }
    single { get<GhostlyDatabase>().authorDao }
    single { get<GhostlyDatabase>().tagDao }
    single { get<GhostlyDatabase>().postAuthorCrossRefDao }
    single { get<GhostlyDatabase>().postTagCrossRefDao }
    single { get<GhostlyDatabase>().remoteKeysDao }
}