package com.example.swapbook.network


import com.squareup.moshi.Json
import java.util.*

data class Post(

        val ID_post: String,
//        val publishing_date: Date,
//        val closing_date: Date,
//        val description: String,
        @Json(name = "img_src") val imgSrcUrl: String,
//        val city: String,
//        val price: Double,
//
//        //book
//        val author: String,
//        val title: String,
//        val publishing_house: String,
//        val physical_desc: String,
//        val publishing_year: Date,
//        val condition: String,
//        val lenght: Double,
//        val height: Double,
//        val width: Double,
//        val genre: String,
)