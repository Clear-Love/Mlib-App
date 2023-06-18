package com.lmio.mlib.entity

import android.content.Context
import android.content.SharedPreferences
import java.util.Date

data class Account(
    val userId: Int,
    val role: String,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val introduction: String,
    val nickname: String,
    val level: Int,
    val exp: Int,
    val avatar: String,
    val gender: String
) {
    companion object {
        private const val PREFS_NAME = "userInfo"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_ROLE = "role"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE_NUMBER = "phone_number"
        private const val KEY_INTRODUCTION = "introduction"
        private const val KEY_NICKNAME = "nickname"
        private const val KEY_LEVEL = "level"
        private const val KEY_EXP = "exp"
        private const val KEY_AVATAR = "avatar"
        private const val KEY_GENDER = "gender"

        private lateinit var sharedPreferences: SharedPreferences

        fun init(context: Context):SharedPreferences {
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return sharedPreferences
        }

        fun getRef(): SharedPreferences {
            return sharedPreferences
        }

        fun save(account: Account) {
            val editor = sharedPreferences.edit()
            editor.putInt(KEY_USER_ID, account.userId)
            editor.putString(KEY_ROLE, account.role)
            editor.putString(KEY_USERNAME, account.username)
            editor.putString(KEY_EMAIL, account.email)
            editor.putString(KEY_PHONE_NUMBER, account.phoneNumber)
            editor.putString(KEY_INTRODUCTION, account.introduction)
            editor.putString(KEY_NICKNAME, account.nickname)
            editor.putInt(KEY_LEVEL, account.level)
            editor.putInt(KEY_EXP, account.exp)
            editor.putString(KEY_AVATAR, account.avatar)
            editor.putString(KEY_GENDER, account.gender)
            editor.apply()
        }

        fun get(): Account {
            val userId = sharedPreferences.getInt(KEY_USER_ID, -1)
            val role = sharedPreferences.getString(KEY_ROLE, "") ?: ""
            val username = sharedPreferences.getString(KEY_USERNAME, "") ?: ""
            val email = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
            val phoneNumber = sharedPreferences.getString(KEY_PHONE_NUMBER, "") ?: ""
            val introduction = sharedPreferences.getString(KEY_INTRODUCTION, "") ?: ""
            val nickname = sharedPreferences.getString(KEY_NICKNAME, "") ?: ""
            val level = sharedPreferences.getInt(KEY_LEVEL, 0)
            val exp = sharedPreferences.getInt(KEY_EXP, 0)
            val avatar = sharedPreferences.getString(KEY_AVATAR, "") ?: ""
            val gender = sharedPreferences.getString(KEY_GENDER, "") ?: ""
            return Account(
                userId,
                role,
                username,
                email,
                phoneNumber,
                introduction,
                nickname,
                level,
                exp,
                avatar,
                gender
            )
        }
    }
}