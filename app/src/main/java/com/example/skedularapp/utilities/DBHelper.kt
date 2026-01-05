package com.example.skedularapp.utilities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "skedular.db"
        private const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: DBHelper? = null

        fun getInstance(context: Context): DBHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DBHelper(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }

        public object Tables {
            const val SETTINGS = "Settings"
            const val SUBJECT = "Subject"
            const val EVENT = "Event"
        }

        public object SettingsColumns {
            const val ID = "id"
            const val USERNAME = "username"
            const val THEME = "theme"
        }

        public object SubjectColumns {
            const val ID = "id"
            const val NAME = "name"
            const val COLOR = "color"
        }

        public object EventColumns {
            const val ID = "id"
            const val SUBJECT_ID = "subject_id"
            const val TITLE = "title"
            const val DUE_DATE = "due_date"
            const val REMINDER = "reminder"
            const val ACTIVITY = "activity"
            const val DESCRIPTION = "description"
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE ${Tables.SETTINGS}(
                ${SettingsColumns.ID} INTEGER PRIMARY KEY NOT NULL UNIQUE,
                ${SettingsColumns.USERNAME} TEXT NOT NULL,
                ${SettingsColumns.THEME} TEXT NOT NULL
            );
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE ${Tables.SUBJECT}(
                ${SubjectColumns.ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                ${SubjectColumns.NAME} TEXT NOT NULL,
                ${SubjectColumns.COLOR} TEXT NOT NULL
            );
        """.trimIndent())

        db.execSQL("""
            CREATE TABLE ${Tables.EVENT}(
                ${EventColumns.ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                ${EventColumns.SUBJECT_ID} INTEGER,
                ${EventColumns.TITLE} TEXT NOT NULL,
                ${EventColumns.DUE_DATE} TEXT NOT NULL,
                ${EventColumns.REMINDER} TEXT NOT NULL,
                ${EventColumns.ACTIVITY} TEXT NOT NULL,
                ${EventColumns.DESCRIPTION} TEXT NOT NULL,
                FOREIGN KEY (${EventColumns.SUBJECT_ID}) 
                REFERENCES ${Tables.SUBJECT}(${SubjectColumns.ID}) 
                ON DELETE SET NULL
            );
        """.trimIndent())

        db.execSQL("""
            INSERT INTO ${Tables.SETTINGS} 
            (${SettingsColumns.ID}, ${SettingsColumns.USERNAME}, ${SettingsColumns.THEME}) 
            VALUES(1, 'user', 'system');
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (true) {
            db.execSQL("DROP TABLE IF EXISTS ${Tables.SETTINGS}")
            db.execSQL("DROP TABLE IF EXISTS ${Tables.SUBJECT}")
            db.execSQL("DROP TABLE IF EXISTS ${Tables.EVENT}")
            onCreate(db)
        }
    }
}