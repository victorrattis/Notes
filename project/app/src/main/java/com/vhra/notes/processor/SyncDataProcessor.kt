package com.vhra.notes.processor

import android.content.ContentResolver
import android.util.Log
import com.vhra.notes.data.Note
import com.vhra.notes.data.NoteContract
import com.vhra.notes.data.toContentValues
import org.json.JSONException
import org.json.JSONObject

class SyncDataProcessor(resolver: ContentResolver?) : Processor {
    private val mResolver: ContentResolver? = resolver

    override fun process(response: String?) {
        val json = JSONObject(response)
        val payload = json.getJSONObject("payload")
        val jsonArray = payload.getJSONArray("notes")
        for (i in 0..jsonArray.length()-1) {
            try {
                val oneObject = jsonArray.getJSONObject(i)
                val note = Note(
                    oneObject.getInt("_id"),
                    oneObject.getInt("dbid"),
                    oneObject.getString("title"),
                    oneObject.getString("body")
                )

                mResolver?.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
            } catch (e: JSONException) {
                Log.e("debug", e.message)
            }
        }
    }
}