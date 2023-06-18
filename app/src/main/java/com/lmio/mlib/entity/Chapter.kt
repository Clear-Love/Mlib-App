package com.lmio.mlib.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Chapter (
    val title: String,
    @PrimaryKey val url: String,
    val bookId: Int,
    val position: Int,
    val read: Boolean = false,
    val lastReadPosition: Int = 0, // 最后阅读位置
    val lastReadOffset: Int = 0, // 最后阅读偏移值
    val body: String //章节内容
)