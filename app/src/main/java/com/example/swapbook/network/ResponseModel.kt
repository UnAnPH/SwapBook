package com.example.swapbook.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseModel (
//
//    @field:Json(name = "result")
//    var result: ArrayList<Post>? = ArrayList(),

    @field:Json(name = "code")
    var code: String? = "-1",

    @field:Json(name = "code")
    var message: String? = "UNKNOWN MESSAGE"
): Parcelable {}

//import com.google.gson.annotations.SerializedName
//
//class ResponseModel {
//    /**
//     * Our ResponseModel attributes
//     */
//    @SerializedName("result")
//    var result: ArrayList<Post>? = ArrayList()
//
//    @SerializedName("code")
//    var code: String? = "-1"
//
//    @SerializedName("message")
//    var message: String? = "UNKNOWN MESSAGE"
//
//}