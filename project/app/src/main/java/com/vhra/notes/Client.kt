package com.vhra.notes

import android.content.Context
import android.content.Intent
import com.vhra.notes.data.Note
import com.vhra.notes.data.NoteContract
import com.vhra.notes.data.toContentValues
import com.vhra.notes.service.SyncService

class Client(content: Context) {
    private var mContext: Context? = content

    public fun syncData() {
        val intent: Intent = Intent(
                SyncService.SYNC_DATA_ACTION,
                null,
                mContext,
                SyncService::class.java)
        mContext?.startService(intent)
    }

    public fun addNote(title: String, body: String) {
        val note = Note(-1, 0, title, body)
        mContext?.contentResolver?.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
    }
}