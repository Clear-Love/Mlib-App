package com.lmio.mlib.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookWithContext(
    var isbn: String?, // ISBN编号
    var bookId: Int,
    var title: String?, // 书名
    var author: String?, // 作者
    var publisher: String?, // 出版社名称（外码）
    var publishDate: String?, // 出版时间
    var language: String?, // 语言
    var collectCount: Int, // 收藏次数
    var description: String?, // 简介
    var price: String?, // 价格
    var coverImage: String?, // 封面
    var ratingNUm: String?, // 评分
    var completed: Boolean = false, // 是否读完
    var inLibrary: Boolean = false, //是否在书架
    val lastReadEpochTimeMilli: Long = 0,
    val chaptersCount: Int,
    val chaptersReadCount: Int
) : Parcelable
