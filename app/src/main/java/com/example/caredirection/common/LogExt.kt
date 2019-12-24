package com.example.caredirection.common

import android.util.Log

private const val TAG = "cadi"

fun String.logDebug() {
    Log.d(TAG, this)
}

fun String.logError() {
    Log.e(TAG, this)
}

fun String.logWarn() {
    Log.w(TAG, this)
}