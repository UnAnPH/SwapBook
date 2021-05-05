package com.example.swapbook.helpers

import android.content.Context
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private var retrofit: Retrofit? = null

object Utils {
    private const val base_url = "http://10.0.2.2/SwapBook/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }

    fun show(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }
}