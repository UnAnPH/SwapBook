package com.example.swapbook.helpers

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.swapbook.database.BookDetail

@BindingAdapter("titleFormatted")
fun TextView.setTitle(item: BookDetail){
    text = item.title
}

@BindingAdapter("priceFormatted")
fun TextView.setPriceFormatted(item: BookDetail) {

}

@BindingAdapter("bookImage")
fun ImageView.setBookImage(item: BookDetail) {

    }
