package com.vhra.notes.data

import android.content.ContentResolver.CURSOR_DIR_BASE_TYPE
import android.content.ContentResolver.CURSOR_ITEM_BASE_TYPE
import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

object NoteContract {
    val CONTENT_AUTHORITY = "com.vhra.notes"

    val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

    object Note {
        val TABLE_NAME = "notes"
        val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build()
        val NOTES_DATABASE_ID = 0

        val CONTENT_TYPE = "$CURSOR_DIR_BASE_TYPE/$CONTENT_AUTHORITY/$TABLE_NAME"
        val CONTENT_ITEM_TYPE = "$CURSOR_ITEM_BASE_TYPE/$CONTENT_AUTHORITY/$TABLE_NAME"

        object Key {
            val _ID = BaseColumns._ID
            val TITLE = "title"
            val BODY = "body"
            val DATABASE_ID = "dbid"
        }

        fun buildWithId(id: Long): Uri {
            return ContentUris.withAppendedId(CONTENT_URI, id)
        }

        fun getIdFromUri(uri: Uri): Long {
            return ContentUris.parseId(uri)
        }
    }
}