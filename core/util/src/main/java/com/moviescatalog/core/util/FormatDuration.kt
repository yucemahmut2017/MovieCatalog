package com.moviescatalog.core.util

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
    fun FormatDuration(millis: Long): String {
        val totalSeconds = millis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d", minutes, seconds)
    }