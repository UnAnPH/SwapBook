package com.example.swapbook.dataclasses

import java.util.*
import java.util.concurrent.locks.Condition

class Book(
        val ID_book: Int,
        val author: String,
        val title: String,
        val publishing_house: String,
        val physical_desc: String,
        val publishing_year: Date,
        val condition: String,
        val lenght: Double,
        val height: Double,
        val width: Double,
        val genre: String,
)