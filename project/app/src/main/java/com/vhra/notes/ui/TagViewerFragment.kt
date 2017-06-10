package com.vhra.notes.ui

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vhra.notes.R
import com.vhra.notes.ui.adapter.TagViewerAdapter

class TagViewerFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View? = inflater?.inflate(R.layout.tags_viewer, container, false)

        val tagViewer = view?.findViewById(R.id.tagsViewer) as RecyclerView?
        val adapter: TagViewerAdapter = TagViewerAdapter(activity)
        tagViewer?.adapter = adapter
        tagViewer?.layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.HORIZONTAL, false)

        adapter.tagClickListener = {
            Log.d("debug", "onItemClick: $it")
        }

        return view as View
    }
}