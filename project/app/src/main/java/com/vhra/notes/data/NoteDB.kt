package com.vhra.notes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDB(context: Context?) :
        SQLiteOpenHelper(context, NoteDB.DATABASE_NAME, null, NoteDB.DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "note.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createNoteTable = "CREATE TABLE ${NoteContract.Note.TABLE_NAME} (" +
                "${NoteContract.Note.Key._ID} INTEGER PRIMARY Key, " +
                "${NoteContract.Note.Key.TITLE} TEXT NOT NULL, " +
                "${NoteContract.Note.Key.BODY} TEXT NOT NULL, " +
                " UNIQUE (${NoteContract.Note.Key.TITLE}) ON CONFLICT REPLACE" +
                ");"

        db?.execSQL(createNoteTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE_NAME IF EXISTS ${NoteContract.Note.TABLE_NAME}")
        onCreate(db)
    }
}