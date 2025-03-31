package com.moviescatalog.core.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun formatReleaseDate(releaseDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

        return try {
            val date = inputFormat.parse(releaseDate)
            outputFormat.format(date)
        } catch (e: Exception) {
            releaseDate
        }
    }
}