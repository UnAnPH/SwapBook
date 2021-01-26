package com.example.swapbook.network

import com.squareup.moshi.Json

data class MarsProperty(
    val ID_post: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "img_src") val imgSrcUrl: String
)