package com.vhra.notes

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.vhra.notes.data.NoteContract

class NoteViewAdapter(context: Context, cursor: Cursor?, flags: Int) :
        CursorAdapter(context, cursor, flags) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.note_item_layout, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val titleTextView = view?.findViewById(R.id.noteItem) as TextView
        val bodyTextView = view?.findViewById(R.id.body) as TextView

        val TITLE_COL_INDEX = cursor?.getColumnIndex(NoteContract.Note.Key.TITLE) as Int
        val taskTitle = cursor?.getString(TITLE_COL_INDEX) ?: ""

        val BODY_COL_INDEX = cursor?.getColumnIndex(NoteContract.Note.Key.BODY) as Int
        val noteBody = cursor?.getString(BODY_COL_INDEX) ?: ""

        titleTextView.text = taskTitle
        bodyTextView.text = noteBody
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("debug", "getView: $position")
        return super.getView(position, convertView, parent)
    }
}