package com.lmio.mlib.utils

import android.view.View
import com.lmio.mlib.entity.ApiResponse
import com.lmio.mlib.services.ApiService
import okhttp3.CookieJar
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object Requests {
    private const val BASE_URL = "https://book.lmio.xyz"
    private lateinit var request: ApiService

    fun getApiService(): ApiService {
        return request
    }

    fun Init() {
        val timeoutSeconds = 20L // 超时时间，以秒为单位

        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        val cookieJar: CookieJar = JavaNetCookieJar(cookieManager)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // 设置自定义的 OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        request =  retrofit.create(ApiService::class.java)
    }

    fun <T> enqueue(call: Call<ApiResponse<T>>, view: View,
                    success: ((String, T?) -> Unit)? = null,
                    failure: ((String, T?) -> Unit)? = null,
                    error: (() -> Unit)? = null) {
        call.enqueue(object : Callback<ApiResponse<T>> {
            override fun onResponse(call: Call<ApiResponse<T>>, response: Response<ApiResponse<T>>) {
                val result: ApiResponse<T>? = response.body()
                result?.let {
                    val message = it.message
                    val data: T = it.data
                    if (it.success) {
                        success?.invoke(message, data) ?: SnackbarUtil.showSuccessMessage(view, message)
                    }else {
                        failure?.invoke(message, data) ?: SnackbarUtil.showWarningMessage(view, message)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<T>>, t: Throwable) {
                error?.invoke() ?: SnackbarUtil.showErrorMessage(view, "请求超时")
            }
        })
    }
}