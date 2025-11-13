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
