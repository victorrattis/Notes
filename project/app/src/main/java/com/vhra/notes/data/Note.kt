package com.vhra.notes.data

import android.content.ContentValues


data class Note(
    val _id: Int,
    val databaseId: Int,
    val title: String,
    val body: String
)

fun Note.toContentValues(): ContentValues {
    val values = ContentValues()
    values.put(NoteContract.Note.Key.DATABASE_ID, NoteContract.Note.NOTES_DATABASE_ID)
    values.put(NoteContract.Note.Key.TITLE, this.title)
    values.put(NoteContract.Note.Key.BODY, this.body)
    return values
}