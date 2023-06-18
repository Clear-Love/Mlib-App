package com.lmio.mlib.services

import com.lmio.mlib.entity.Account
import com.lmio.mlib.entity.ApiResponse
import com.lmio.mlib.entity.Book
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("/api/auth/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("remember") remember: Boolean
    ): Call<ApiResponse<Account>>

    @FormUrlEncoded
    @POST("/api/auth/valid-register-email")
    fun validateRegisterEmail(
        @Field("email") email: String
    ): Call<ApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/auth/valid-reset-email")
    fun validateResetEmail(
        @Field("email") email: String
    ): Call<ApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/auth/register")
    fun registerUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("code") code: String
    ): Call<ApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/auth/start-reset")
    fun startReset(
        @Field("email") email: String,
        @Field("code") code: String
    ): Call<ApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/auth/do-reset")
    fun resetPassword(
        @Field("password") password: String
    ): Call<ApiResponse<String>>

    @POST("/api/book/search")
    fun searchBooks(@Query("text") text: String): Call<ApiResponse<List<Book>>>
}