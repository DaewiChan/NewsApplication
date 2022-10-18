package com.daewi.newsapplication.common

import java.text.SimpleDateFormat
import java.util.*

open class DateTimeUtil {
    open fun convertDateToString(date: String?): String {
        val sdf = SimpleDateFormat("yyyy/MMM/dd HH:mm:ss", Locale.getDefault())
        val today = Calendar.getInstance()
            .time
        return sdf.format(today)
    }
}