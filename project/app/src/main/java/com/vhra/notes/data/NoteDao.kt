package com.vhra.notes.data

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class NoteDao(db: SQLiteDatabase?) {
    private val mDatabase: SQLiteDatabase? = db

    fun getNoteById(noteId: Int, databaseID: Int = 0): Note? {
        var note: Note? = null
        val sql = "SELECT * FROM notes WHERE notes._id = $noteId AND notes.dbid = $databaseID"
        val cursor: Cursor? = mDatabase?.rawQuery(sql, null)
        if (cursor != null) {
            cursor.moveToFirst()
            note = cursorToNote(cursor)
            cursor.close()
        }

        return note
    }

    private fun cursorToNote(cursor: Cursor): Note? {
        var id: Int = 0
        var dabid: Int = 0
        var title: String = ""
        var body: String = ""

        if (cursor.getColumnIndex(NoteContract.Note.Key._ID) != -1) {
            val index = cursor.getColumnIndexOrThrow(NoteContract.Note.Key._ID)
            id = cursor.getInt(index)
        }

        if (cursor.getColumnIndex(NoteContract.Note.Key.DATABASE_ID) != -1) {
            val index = cursor.getColumnIndexOrThrow(NoteContract.Note.Key.DATABASE_ID)
            dabid = cursor.getInt(index)
        }

        if (cursor.getColumnIndex(NoteContract.Note.Key.DATABASE_ID) != -1) {
            val index = cursor.getColumnIndexOrThrow(NoteContract.Note.Key.TITLE)
            title = cursor.getString(index)
        }

        if (cursor.getColumnIndex(NoteContract.Note.Key.DATABASE_ID) != -1) {
            val index = cursor.getColumnIndexOrThrow(NoteContract.Note.Key.BODY)
            body = cursor.getString(index)
        }

        return Note(id, dabid, title, body)
    }
}