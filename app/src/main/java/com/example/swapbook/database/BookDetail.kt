package com.example.swapbook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_detail_table")
data class BookDetail(
        @PrimaryKey(autoGenerate = true)
        var bookID: Long = 0L,

        @ColumnInfo(name = "author")
        var bookAuthor: String = "",

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "publishing_house")
        var publishingHouse: String = "",

        @ColumnInfo(name = "condition")
        var condition: String = "",

        @ColumnInfo(name = "height")
        var height: Float = 0F,

        @ColumnInfo(name = "lenght")
        var lenght: Float = 0F,

        @ColumnInfo(name = "width")
        var width: Float = 0F,

        @ColumnInfo(name = "genre")
        var genre: String = ""
)


