package com.example.skedularapp.utilities

// DatabaseManager.kt
import android.content.ContentValues
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.example.skedularapp.utilities.DBHelper
import java.util.Date


data class Settings(
    val username: String,
    val theme: String
)

data class Event(
    val title: String,
    val activity: String,
    val subject: String,
    val date: Date,
    val reminder: Date,
    val description: String
)

object DatabaseManager {
    private lateinit var dbHelper: DBHelper

    fun initialize(context: Context) {
        if (!this::dbHelper.isInitialized) {
            dbHelper = DBHelper.getInstance(context.applicationContext)
        }
    }

    suspend fun getSettings() = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            "Settings",
            arrayOf("username", "theme"),
            "id = ?",
            arrayOf("1"),
            null, null, null
        )

        cursor.use {
            return@withContext if (it.moveToFirst()) {

                val username = it.getString(it.getColumnIndexOrThrow("username"))
                val theme = it.getString(it.getColumnIndexOrThrow("theme"))

                Settings(username, theme)

            } else null
        }

    }

    suspend fun updateSettings(username: String, theme: String) = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("username", username)
            put("theme", theme)
        }

        db.update("Settings", values, "id = ?", arrayOf("1"))
    }

    suspend fun addEvent(title: String, activity: String, subject: String, date: Date, description: String) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("title", title)
            put("activity", activity)
            put("subject", subject)
            put("date", toString(date))
            put("reminder", toString(date))
            put("description", description)
        }

        db.insert("Event", null, values)
    }

    suspend fun deleteEvent(id: Int) {
        val db = dbHelper.writableDatabase

        db.delete("Event", "id = ?", arrayOf(id.toString()))
    }

    suspend fun updateEvent(id: Int, title: String, activity: String, subject: String, date: Date, reminder: Date, description: String) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("title", title)
            put("activity", activity)
            put("subject", subject)
            put("date", toString(date))
            put("reminder", toString(date))
            put("description", description)
        }

        db.update("Event", values, "id = ?", arrayOf(id.toString()))
    }

    suspend fun getEvent(id: Int): Event? = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            "Event",
            arrayOf("title", "activity", "subject", "date", "reminder", "description"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        cursor.use {
            return@withContext if (it.moveToFirst()) {
                val title = it.getString(it.getColumnIndexOrThrow("title"))
                val activity = it.getString(it.getColumnIndexOrThrow("activity"))
                val subject = it.getString(it.getColumnIndexOrThrow("subject"))
                val date = it.getString(it.getColumnIndexOrThrow("date"))
                val reminder = it.getString(it.getColumnIndexOrThrow("reminder"))
                val description = it.getString(it.getColumnIndexOrThrow("description"))

                Event(title, activity, subject, parseDate(date), parseDate(reminder), description)
            } else null
        }
    }
}

