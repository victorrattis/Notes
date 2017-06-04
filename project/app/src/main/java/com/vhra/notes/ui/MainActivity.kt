package com.vhra.notes.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.vhra.notes.Client
import com.vhra.notes.R

class MainActivity : AppCompatActivity() {
    private var mCount: Int  = 0
    private var mClient: Client? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mClient = Client(this)
    }

    override fun onResume() {
        super.onResume()
    }

    fun addListView(view: View) {
        Log.d("debug", "addListView")
        mCount++
        mClient?.addNote("Title " + mCount, "Note has created by device")
    }

    fun fetchServer(view: View) {
        Log.d("debug", "fetchServer")
        mClient?.syncData()
    }
}
