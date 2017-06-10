package com.vhra.notes.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vhra.notes.R

class TagViewerAdapter(context: Context)
        : RecyclerView.Adapter<TagViewerAdapter.TagViewHolder>() {

    var tagClickListener: ((String) -> Unit)? = null

    private val mContext: Context = context

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): TagViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.tag_item, parent, false)
        return TagViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.tagNameView?.text = "tag[$position]"
        holder.view?.setOnClickListener({
            tagClickListener?.invoke("tag[$position]")
        })
    }

    override fun getItemCount(): Int {
        return 10
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
