package com.example.swapbook.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDatabaseDao {
    @Insert
    suspend fun insert(book: BookDetail)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param night new value to write
     */
    @Update
    suspend fun update(book: BookDetail)

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM book_detail_table")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM book_detail_table ORDER BY bookID DESC")
    fun getAllBooks(): LiveData<List<BookDetail>>

    /**
     * Selects and returns the latest night.
     */
    @Query("SELECT * FROM book_detail_table ORDER BY bookID DESC LIMIT 1")
    suspend fun getLastBook(): BookDetail?
}
