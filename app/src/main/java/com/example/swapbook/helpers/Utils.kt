package com.example.swapbook.helpers

import android.content.Context
import android.widget.Toast

object Utils {

    fun show(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
    }
}