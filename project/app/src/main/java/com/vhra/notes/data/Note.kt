package com.vhra.notes.data

import android.content.ContentValues


data class Note(
    val title: String,
    val body: String
)

fun Note.toContentValues(): ContentValues {
    val values = ContentValues()
    values.put(NoteContract.Note.Key.TITLE, this.title)
    values.put(NoteContract.Note.Key.BODY, this.body)
    return values
}