package com.vhra.notes.data

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?) {
    private val mContext = context
    private var mDbHelper: SQLiteOpenHelper? = null

    private var tagDao: TagDao? = null

    fun open() {
        mDbHelper = NoteDB(mContext)
        val database = mDbHelper?.writableDatabase
        tagDao = TagDao(database)
    }

    fun close() {
        mDbHelper?.close()
    }
}