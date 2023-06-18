package com.lmio.mlib.ui

import com.lmio.mlib.stores.AppDatabase

interface FragmentCallback {
    fun getDb():AppDatabase
}