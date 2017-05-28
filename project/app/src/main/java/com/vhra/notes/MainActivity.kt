package com.vhra.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vhra.notes.data.Note
import com.vhra.notes.data.NoteContract
import com.vhra.notes.data.toContentValues

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }

    fun addListView(view: View) {
        val size = 26//mNoteAdapter?.count ?: 0
        val note: Note = Note("Note $size", "Description about this note...");
        contentResolver.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
    }
}
