package com.vhra.notes.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class NotePreferences(context: Context?) {
    private val mContext: Context? = context
    private var mPreferences: SharedPreferences? = null

    /**
     * Represents the last sync date with the server, in milliseconds.
     * */
    val SYNC_DATE_PREFERENCES = "sync-date"

    init {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
    }

    fun setSyncDate(value: Long) {
        val editor = mPreferences?.edit()
        editor?.putLong(SYNC_DATE_PREFERENCES, value)
        editor?.apply()
    }

    fun getSyncDate(): Long {
        return mPreferences?.getLong(SYNC_DATE_PREFERENCES, 0) ?: 0
    }
}