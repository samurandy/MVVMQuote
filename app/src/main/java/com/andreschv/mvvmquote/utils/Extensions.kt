package com.andreschv.mvvmquote.utils

import android.content.Context
import android.widget.Toast

class Extensions {
    companion object {
        fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(this, message, duration).show()
        }
    }
}