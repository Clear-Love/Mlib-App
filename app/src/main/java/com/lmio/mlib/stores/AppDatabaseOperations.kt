package com.lmio.mlib.stores

interface AppDatabaseOperations {
    suspend fun <T> transaction(block: suspend () -> T): T
}
