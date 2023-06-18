package com.lmio.mlib.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lmio.mlib.entity.Book
import com.lmio.mlib.entity.Chapter

@Dao
interface ChapterDao {
    @Update
    suspend fun update(chapter: Chapter)

    @Insert
    suspend fun insert(chapter: Chapter)

    // 更新章节标题
    @Query("UPDATE chapter SET title = :title WHERE url = :chapterUrl")
    suspend fun updateChapterTitle(title: String?, chapterUrl: String)

    // 将章节标记为未读
    @Query("UPDATE chapter SET read = 0 WHERE url = :chapterUrl")
    suspend fun markChapterAsUnread(chapterUrl: String)

    // 将章节标记为已读
    @Query("UPDATE chapter SET read = 1 WHERE url = :chapterUrl")
    suspend fun markChapterAsRead(chapterUrl: String)
}