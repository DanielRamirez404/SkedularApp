package com.example.skedularapp.utilities

import java.util.Calendar
import java.util.Date

fun getMondayOfWeek(date: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.firstDayOfWeek = Calendar.MONDAY
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    return calendar.time
}

fun getNextWeek(date: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.WEEK_OF_YEAR, 1)
    return calendar.time
}

fun isWeekend(date: Date): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY
}

fun getDayOfWeekNumber(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    return if (dayOfWeek == Calendar.SUNDAY) {
        7
    } else {
        dayOfWeek - 1
    }
}

fun getDayOfDate(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun getNthDayAfter(date: Date, n: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DAY_OF_YEAR, n)
    return calendar.time
}

fun addTrailingZeros(number: Int, numberOfDigits: Int): String {
    return String.format("%0${numberOfDigits}d", number)
}

fun parseDate(string: String, format: String = "numeric"): Date {
    val calendar = Calendar.getInstance()

    if (format == "numeric") { // format: yyyy-MM-dd hh:mm:ss
        calendar.set(Calendar.YEAR, string.take(4).toInt())
        calendar.set(Calendar.MONTH, string.substring(5, 7).toInt() - 1)
        calendar.set(Calendar.DAY_OF_MONTH, string.substring(8, 10).toInt())
        calendar.set(Calendar.HOUR_OF_DAY, string.substring(11, 13).toInt())
        calendar.set(Calendar.MINUTE, string.substring(14, 16).toInt())
        calendar.set(Calendar.SECOND, string.substring(17, 19).toInt())
    } else if (format == "text") { // format: Mon dd, yyyy hh:mm PM/AM

        val month = when (string.take(3)) {
            "Jan" -> 0
            "Feb" -> 1
            "Mar" -> 2
            "Apr" -> 3
            "May" -> 4
            "Jun" -> 5
            "Jul" -> 6
            "Aug" -> 7
            "Sep" -> 8
            "Oct" -> 9
            "Nov" -> 10
            "Dec" -> 11
            else -> 0
        }

        val isAfterMidday: Boolean = string.substring(19, 21) == "PM"
        val baseHour = string.substring(13, 15).toInt()

        calendar.set(Calendar.YEAR, string.substring(8, 12).toInt())
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, string.substring(4, 6).toInt())
        calendar.set(Calendar.HOUR_OF_DAY, if (isAfterMidday) baseHour + 12 else baseHour)
        calendar.set(Calendar.MINUTE, string.substring(16, 18).toInt())
        calendar.set(Calendar.SECOND, 0)
    }

    return calendar.time
}

fun toString(date: Date, format: String = "numeric"): String {

    if (format == "date") {
        return toString(date).take(10) // format: yyyy-MM-dd
    }

    val calendar = Calendar.getInstance()
    calendar.time = date
    val year = addTrailingZeros(calendar.get(Calendar.YEAR), 4)
    val month = addTrailingZeros(calendar.get(Calendar.MONTH) + 1, 2)
    val day = addTrailingZeros(calendar.get(Calendar.DAY_OF_MONTH), 2)
    val hour = addTrailingZeros(calendar.get(Calendar.HOUR_OF_DAY), 2)
    val minute = addTrailingZeros(calendar.get(Calendar.MINUTE), 2)
    val second = addTrailingZeros(calendar.get(Calendar.SECOND), 2)
    return "$year-$month-$day $hour:$minute:$second"
}

fun toSQLDate(date: Date): java.sql.Date {
    return java.sql.Date(date.time)
}

fun toJavaDate(date: java.sql.Date): Date {
    return Date(date.time)
}