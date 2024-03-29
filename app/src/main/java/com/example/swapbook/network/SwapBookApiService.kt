package com.example.swapbook.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


private const val base_url = "http://10.0.2.2/SwapBook/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .baseUrl(base_url)
    .build()





interface SwapBookApiService {
//    @GET("book")
@GET("index.php")
suspend fun retrieve(): List<Post>

@FormUrlEncoded
@POST("index.php")
    fun insertData(
        @Field("action") action: String?,
        @Field("userID") userID: String?,
        @Field("imgUrl") imgUrl: String?,
        @Field("bookTitle") bookTitle: String?,
        @Field("authorName") authorName: String?,
        @Field("genre") genre: String?,
        @Field("description") description: String?,
        @Field("publishingYear") publishingYear: String?,
        @Field("publishingHouse") publishingHouse: String?,
        @Field("physicalDescription") physicalDescription: String?,
        @Field("condition") condition: String?,
        @Field("lenght") lenght: String?,
        @Field("height") height: String?,
        @Field("width") width: String?,
        @Field("city") city: String?,
        @Field("province") province: String?,
        @Field("price") price: String?
):Call<ResponseModel?>?

    @FormUrlEncoded
    @POST("index.php")
    fun updateData(
        @Field("action") action: String?,
        @Field("bookID") bookID: String?,
        @Field("postID") postID: String?,
        @Field("imgUrl") imgUrl: String?,
        @Field("bookTitle") bookTitle: String?,
        @Field("authorName") authorName: String?,
        @Field("genre") genre: String?,
        @Field("description") description: String?,
        @Field("publishingYear") publishingYear: String?,
        @Field("publishingHouse") publishingHouse: String?,
        @Field("physicalDescription") physicalDescription: String?,
        @Field("condition") condition: String?,
        @Field("lenght") lenght: String?,
        @Field("height") height: String?,
        @Field("width") width: String?,
        @Field("city") city: String?,
        @Field("province") province: String?,
        @Field("price") price: String?
    ):Call<ResponseModel?>?


    @FormUrlEncoded
    @POST("index.php")
    fun insertUser(
        @Field("action") action: String?,
        @Field("ID_user") idUser: String?,
    ):Call<ResponseModel?>?

    @FormUrlEncoded
    @POST("index.php")
    fun getMaxIDPost(
        @Field("action") action: String?,
    ):Call<ResponseModel?>?

    @FormUrlEncoded
    @POST("index.php")
    fun search(
        @Field("action") action: String?,
        @Field("query") query: String?,
        @Field("start") start: String?,
        @Field("limit") limit: String?
    ): List<Post>

    @FormUrlEncoded
    @POST("index.php")
    fun deleteData(
        @Field("action") action: String?,
        @Field("postId") postId: String?
    ): Call<ResponseModel?>?
}

object SwapBookApi {
    val retrofitService : SwapBookApiService by lazy {
        retrofit.create(SwapBookApiService::class.java) }
}