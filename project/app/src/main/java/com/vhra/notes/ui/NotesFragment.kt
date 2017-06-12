package com.vhra.notes.ui

import android.app.Fragment
import android.app.LoaderManager.LoaderCallbacks
import android.content.Context
import android.content.CursorLoader
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vhra.notes.data.NoteContract
import com.vhra.notes.ui.adapter.NoteViewAdapter
import com.vhra.notes.R

class NotesFragment : Fragment(), LoaderCallbacks<Cursor> {
    override fun onCreateLoader(id: Int, args: Bundle?): android.content.Loader<Cursor>? {
        return CursorLoader(activity.applicationContext,
                NoteContract.Note.CONTENT_URI,
                null, null, null, null)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onLoadFinished(loader: android.content.Loader<Cursor>?, data: Cursor?) {
        mNoteAdapter?.changeCursor(data)
    }

    override fun onLoaderReset(loader: android.content.Loader<Cursor>?) {
        mNoteAdapter?.changeCursor(null)
    }

    var mNotesView: RecyclerView? = null

    var mNoteAdapter: NoteViewAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View? = inflater?.inflate(R.layout.note_board, container, false)

        mNotesView = view?.findViewById(R.id.notesView) as RecyclerView?

        mNoteAdapter = NoteViewAdapter(activity, null)
        mNotesView?.adapter = mNoteAdapter
        mNotesView?.layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.VERTICAL, false)
//        mNotesView?.layoutManager = StaggeredGridLayoutManager(3, 1)

        mNoteAdapter?.onClickListener = {
            noteId: Int, dbId: Int ->
                val intent = Intent(activity, NoteEditorActivity::class.java)
                intent.putExtra("note-id", noteId)
                intent.putExtra("database-id", dbId)
                activity.startActivity(intent)
            }

        loaderManager.initLoader(0, null, this)
        return view as View
    }
}