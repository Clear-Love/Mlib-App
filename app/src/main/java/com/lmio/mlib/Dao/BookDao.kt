package com.lmio.mlib.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lmio.mlib.entity.Book
import com.lmio.mlib.entity.BookWithContext
import com.lmio.mlib.entity.Chapter
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM Book")
    suspend fun getAll(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: List<Book>)

    @Query("SELECT * FROM Book WHERE bookId = :id")
    suspend fun getById(id: Int): Book?

    @Query("SELECT * FROM Book WHERE inLibrary == 1")
    suspend fun getAllInLibrary(): List<Book>

    @Query("SELECT * FROM Book WHERE inLibrary == 1")
    fun booksInLibraryFlow(): Flow<List<Book>>

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    // 根据 bookId 删除 Book
    @Query("DELETE FROM Book WHERE bookId = :bookId")
    suspend fun deleteByBookId(bookId: Int)

    // 查询某本书的所有章节
    @Query("SELECT * FROM chapter WHERE bookId = :bookId")
    suspend fun getChaptersByBookId(bookId: Int): List<Chapter?>?

    // 查询所有章节
    @Query("SELECT * FROM chapter")
    suspend fun getAllChapters(): List<Chapter?>?

    // 查询所有书籍，按最后阅读时间逆序
    @Query("SELECT * FROM book ORDER BY lastReadEpochTimeMilli DESC")
    suspend fun getAllBooksByLastReadTime(): List<Book>

    @Query("SELECT EXISTS(SELECT * FROM Book WHERE bookId == :bookId AND inLibrary == 1)")
    suspend fun existInLibrary(bookId: Int): Boolean

    // 查询
    @Query(
        """
        SELECT Book.*, COUNT(Chapter.read) AS chaptersCount, SUM(Chapter.read) AS chaptersReadCount
        FROM Book
        LEFT JOIN Chapter ON Chapter.bookId = Book.bookId
        WHERE Book.inLibrary == 1
        GROUP BY Book.bookId
    """
    )
    fun getBooksInLibraryWithContextFlow(): Flow<List<BookWithContext>>
}