package com.vhra.notes.ui.adapter

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vhra.notes.R
import com.vhra.notes.data.NoteContract

class NoteViewAdapter(context: Context, cursor: Cursor?) :
        CursorRecyclerAdapter<NoteViewAdapter.SimpleViewHolder>(cursor) {

    var mContext: Context? = context

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): SimpleViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.note_item, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, cursor: Cursor?) {
        val TITLE_COL_INDEX = cursor?.getColumnIndex(NoteContract.Note.Key.TITLE) as Int
        val taskTitle = cursor?.getString(TITLE_COL_INDEX) ?: ""

        val BODY_COL_INDEX = cursor?.getColumnIndex(NoteContract.Note.Key.BODY) as Int
        val noteBody = cursor?.getString(BODY_COL_INDEX) ?: ""

        holder.titleTextView?.text = taskTitle
        holder.bodyTextView?.text = noteBody
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var titleTextView: TextView? = null
        public var bodyTextView: TextView? = null

        init {
            titleTextView = itemView.findViewById(R.id.noteItem) as TextView
            bodyTextView = itemView.findViewById(R.id.body) as TextView
        }
    }
}