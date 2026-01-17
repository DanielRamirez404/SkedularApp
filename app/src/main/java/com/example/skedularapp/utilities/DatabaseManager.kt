package com.example.skedularapp.utilities

// DatabaseManager.kt
import android.content.ContentValues
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.example.skedularapp.utilities.DBHelper


data class Settings(
    val username: String,
    val theme: String
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
}

