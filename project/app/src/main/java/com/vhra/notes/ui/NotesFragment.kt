package com.vhra.notes.ui

import android.app.Fragment
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.vhra.notes.data.NoteContract
import com.vhra.notes.NoteViewAdapter
import com.vhra.notes.R

class NotesFragment : Fragment(), LoaderCallbacks<Cursor> {
    override fun onCreateLoader(id: Int, args: Bundle?): android.content.Loader<Cursor>? {
        return CursorLoader(activity.applicationContext,
                NoteContract.Note.CONTENT_URI,
                null, null, null, null)
    }

    override fun onLoadFinished(loader: android.content.Loader<Cursor>?, data: Cursor?) {
        mNoteAdapter?.swapCursor(data)
    }

    override fun onLoaderReset(loader: android.content.Loader<Cursor>?) {
        mNoteAdapter?.swapCursor(null)
    }

    var mNotesView: ListView? = null

    var mNoteAdapter: NoteViewAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View? = inflater?.inflate(R.layout.fragment_note_list, container, false)

        mNotesView = view?.findViewById(R.id.notesView) as ListView?

        mNoteAdapter = NoteViewAdapter(activity, null, 0)
        mNotesView?.adapter = mNoteAdapter

        loaderManager.initLoader(0, null, this)

        return view as View
    }
}