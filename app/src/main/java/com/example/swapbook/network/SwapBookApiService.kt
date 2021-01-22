package com.example.swapbook.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val base_url = "http://10.0.2.2/swapbook/"

private const val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SwapBookApiService {
//    @GET("book")
@GET("realestate")
suspend fun getProperties():
        List<MarsProperty>
}

object SwapBookApi {
    val retrofitService : SwapBookApiService by lazy {
        retrofit.create(SwapBookApiService::class.java) }
}