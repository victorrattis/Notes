package com.vhra.notes.ui.adapter

import android.content.Context
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vhra.notes.R
import com.vhra.notes.data.TagSchema


class TagViewerCursosAdapter(context: Context, cursor: Cursor?) :
        CursorRecyclerAdapter<TagViewerCursosAdapter.TagViewHolder>(cursor)  {
    var mContext: Context? = context

    var onClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int)
                : TagViewerCursosAdapter.TagViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.tag_item, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewerCursosAdapter.TagViewHolder, cursor: Cursor?) {
        val TAG_NAME = cursor?.getColumnIndex(TagSchema.TAG_NAME) as Int
        val tagName = cursor?.getString(TAG_NAME) ?: ""
        holder.tagNameView?.text = tagName
        holder.view?.setOnClickListener({
            onClickListener?.invoke(tagName)
        })
    }

    inner class TagViewHolder(itemView: View?)
        : RecyclerView.ViewHolder(itemView) {
        var view: View? = itemView
        var tagNameView: TextView? = null

        init {
            tagNameView = itemView?.findViewById(R.id.tagItem) as TextView
        }
    }
}