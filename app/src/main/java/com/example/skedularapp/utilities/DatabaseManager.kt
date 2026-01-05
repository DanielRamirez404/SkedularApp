package com.example.skedularapp.utilities

// DatabaseManager.kt
import android.content.ContentValues
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import com.example.skedularapp.utilities.DBHelper

object DatabaseManager {
    private lateinit var dbHelper: DBHelper

    fun initialize(context: Context) {
        if (!this::dbHelper.isInitialized) {
            dbHelper = DBHelper.getInstance(context.applicationContext)
        }
    }

    suspend fun getUsername(): String = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "Settings",
            arrayOf("username"),
            "id = ?",
            arrayOf("1"),
            null, null, null
        )

        cursor.use {
            return@withContext if (it.moveToFirst()) {
                it.getString(it.getColumnIndexOrThrow("username"))
            } else "user"
        }
    }
}

