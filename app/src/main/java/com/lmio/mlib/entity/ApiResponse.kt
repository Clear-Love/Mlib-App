package com.lmio.mlib.entity

data class ApiResponse<T> (
    val success: Boolean,
    val message: String,
    val status: Int,
    val data: T
)