package com.example.swapbook.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class ResponseModel {
    /**
     * Our ResponseModel attributes
//     */
//    @SerializedName("result")
//    var result: ArrayList<Scientist>? = ArrayList()

    @SerializedName("code")
    var code: String? = "-1"

    @SerializedName("message")
    var message: String? = "UNKNOWN MESSAGE"

}
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