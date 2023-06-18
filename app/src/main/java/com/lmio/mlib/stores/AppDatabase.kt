package com.lmio.mlib.stores

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.room.withTransaction
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lmio.mlib.Dao.BookDao
import com.lmio.mlib.Dao.ChapterDao
import com.lmio.mlib.entity.Book
import com.lmio.mlib.entity.Chapter
import java.io.InputStream

@Database(entities = [Book::class, Chapter::class], version = 2)
abstract class AppDatabase : RoomDatabase(), AppDatabaseOperations{
    abstract fun bookDao(): BookDao
    abstract fun chapterDao(): ChapterDao

    // 原子操作
    override suspend fun <T> transaction(block: suspend () -> T): T = withTransaction(block)

    companion object {
        private const val DATABASE_NAME = "Lib"
        // 从数据库文件创建实例
        fun createRoom(ctx: Context) = Room
            .databaseBuilder(ctx, AppDatabase::class.java, DATABASE_NAME)
            .addMigrations(*migrations())
            .build()

        // 从输入流创建实例
        fun createRoomFromStream(ctx: Context, inputStream: InputStream) = Room
            .databaseBuilder(ctx, AppDatabase::class.java, DATABASE_NAME)
            .addMigrations(*migrations())
            .createFromInputStream { inputStream }
            .build()

        private fun migrations(): Array<out Migration> {
            return arrayOf(
                object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        // 更新数据库架构
                    }
                }
            )
        }
    }
}