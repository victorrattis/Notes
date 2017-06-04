package com.vhra.notes.data

import android.content.ContentValues


data class Note(
    val _id: Int = -1,
    val databaseId: Int,
    val title: String,
    val body: String
)

fun Note.toContentValues(): ContentValues {
    val values = ContentValues()
    if (this._id != -1) {
        values.put(NoteContract.Note.Key._ID, this._id)
    }
    values.put(NoteContract.Note.Key.DATABASE_ID, this.databaseId)
    values.put(NoteContract.Note.Key.TITLE, this.title)
    values.put(NoteContract.Note.Key.BODY, this.body)
    return values
}