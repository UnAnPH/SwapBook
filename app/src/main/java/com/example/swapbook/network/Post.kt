package com.example.swapbook.network


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Post(

        val ID_post: String,
        val ID_book: String,
        val publishing_date: String,
//      val closing_date: String,
        val description: String,
        @Json(name = "img_src") val imgSrcUrl: String,
        val city: String,
        val province: String,
        var price: String,
        val state: Int,

        //book
        val author: String,
        val title: String,
        val publishing_house: String,
        val physical_desc: String,
        val publishing_year: String,
        val condition: String,
        val lenght: String,
        val height: String,
        val width: String,
        val genre_desc: String,

        //user
        val ID_creator: String?,
        val name: String?,
): Parcelable {}