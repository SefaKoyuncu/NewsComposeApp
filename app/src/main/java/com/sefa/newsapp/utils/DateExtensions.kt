package com.sefa.newsapp.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Date
import java.text.ParseException

fun String.toFormattedDate(): String? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(this, formatter)
            date.format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH))
        } catch (e: Exception) {
            // Handle parsing or formatting exceptions
            "Invalid date"
        }
    } else {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)
            val date = inputFormat.parse(this)
            date?.let { outputFormat.format(it) }
        } catch (e: ParseException) {
            // Handle parsing exceptions
            "Invalid date"
        }
    }
}
