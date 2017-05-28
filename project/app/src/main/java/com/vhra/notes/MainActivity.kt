package com.vhra.notes

import android.content.ContentValues
import android.content.CursorLoader
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.vhra.notes.data.Note
import com.vhra.notes.data.NoteContract
import com.vhra.notes.data.toContentValues

class MainActivity : AppCompatActivity(), android.app.LoaderManager.LoaderCallbacks<Cursor> {
    override fun onCreateLoader(id: Int, args: Bundle?): android.content.Loader<Cursor>? {
        return CursorLoader(applicationContext,
                NoteContract.Note.CONTENT_URI,
                null, null, null, null)
    }

    override fun onLoadFinished(loader: android.content.Loader<Cursor>?, data: Cursor?) {
        mNoteAdpater?.swapCursor(data)
    }

    override fun onLoaderReset(loader: android.content.Loader<Cursor>?) {
        mNoteAdpater?.swapCursor(null)
    }

    var mNotesView: ListView? = null

    var mNoteAdpater: NoteViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNotesView = findViewById(R.id.notesView) as ListView?

        mNoteAdpater = NoteViewAdapter(this, null, 0)
        mNotesView?.adapter = mNoteAdpater

        loaderManager.initLoader(0, null, this)
    }

    override fun onResume() {
        super.onResume()
    }

    fun addListView(view: View) {
        val size = 26//mNoteAdpater?.count ?: 0
        val note: Note = Note("Note $size", "Description about this note...");
        contentResolver.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
    }
}
