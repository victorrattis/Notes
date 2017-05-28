package com.vhra.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vhra.notes.data.Note
import com.vhra.notes.data.NoteContract
import com.vhra.notes.data.toContentValues

class MainActivity : AppCompatActivity() {
    var mCount = 32

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }

    fun addListView(view: View) {
        val note: Note = Note("Note $mCount", "Description about this note...")
        mCount++
        contentResolver.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
    }
}
