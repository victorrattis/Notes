package com.vhra.notes.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import com.vhra.notes.R
import com.vhra.notes.data.NoteDao
import com.vhra.notes.data.NoteDatabase

class NoteEditorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_editor_layout)

        val noteId = intent?.getIntExtra("note-id", -1) ?: -1
        val databaseId = intent?.getIntExtra("database-id", -1) ?: -1

        val noteDao = NoteDao(NoteDatabase(this).writableDatabase)
        val note = noteDao.getNoteById(noteId, databaseId)

        val noteTitleView = findViewById(R.id.note_title) as EditText?
        val noteBodyView = findViewById(R.id.note_body) as EditText?

        noteTitleView?.setText(note?.title, TextView.BufferType.EDITABLE)
        noteBodyView?.setText(note?.body, TextView.BufferType.EDITABLE)
    }
}