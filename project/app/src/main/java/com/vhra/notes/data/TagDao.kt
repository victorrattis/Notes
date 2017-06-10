package com.vhra.notes.data

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class TagDao(db: SQLiteDatabase?) {
    private val mDatabase: SQLiteDatabase? = db

    fun getAll(): Cursor? {
        val sql = "SELECT * FROM ${TagSchema.TABLE_NAME}"
        return mDatabase?.rawQuery(sql, null)
    }
}