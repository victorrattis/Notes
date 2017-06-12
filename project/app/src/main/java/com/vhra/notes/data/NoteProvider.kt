package com.vhra.notes.data

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.UriMatcher
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.util.Log


class NoteProvider : ContentProvider() {
    companion object {
        // helper constants for use with the UriMatcher
        private val NOTE_LIST: Int = 1
        private val NOTE_ID: Int = 2

        fun createUriMatcher(): UriMatcher {
            val matcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            val authority = NoteContract.CONTENT_AUTHORITY

            matcher.addURI(authority, NoteContract.Note.TABLE_NAME, NOTE_LIST)
            matcher.addURI(authority, "${NoteContract.Note.TABLE_NAME}/#", NOTE_ID)
            return matcher
        }

        val sUriMatcher: UriMatcher = createUriMatcher()
    }
    var mNoteDB: NoteDatabase? = null

    override fun onCreate(): Boolean {
        mNoteDB = NoteDatabase(context)
        return true
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri? {
        val db = mNoteDB?.writableDatabase
        val match: Int = sUriMatcher.match(uri)
        var insertionUri: Uri? = null
        var insertedId: Long = 0
        Log.d("debug", "insert: $uri, match: $match")

        when (match) {
            NOTE_LIST -> {
                try {
                    insertedId = db!!.insertOrThrow(NoteContract.Note.TABLE_NAME, null, values)
                    if (insertedId > 0) {
                        insertionUri = NoteContract.Note.buildWithId(insertedId)
                    }
                }catch (e: SQLiteConstraintException){
                    Log.d("debug", e.message)
                }
            }
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }

        context.contentResolver.notifyChange(uri, null)
        return insertionUri
    }

    override fun query(uri: Uri?,
                       projection: Array<out String>?,
                       selection: String?,
                       selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor {
        val db: SQLiteDatabase = mNoteDB?.readableDatabase as SQLiteDatabase
        val match: Int = sUriMatcher.match(uri)
        val cursor: Cursor
        Log.d("debug", "query: $uri, match: $match")

        when (match) {
            NOTE_LIST -> {
                cursor = db.query(NoteContract.Note.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder)
            }
            NOTE_ID -> {
                val id: Long = NoteContract.Note.getIdFromUri(uri as Uri)
                cursor = db.query(NoteContract.Note.TABLE_NAME, projection,
                        "${NoteContract.Note.Key._ID} = ?", arrayOf(id.toString()), null, null, sortOrder)
            }
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }

        cursor?.setNotificationUri(context.contentResolver, uri)
        return cursor
    }

    override fun update(uri: Uri?,
                        values: ContentValues?,
                        selection: String?,
                        selectionArgs: Array<out String>?): Int {
        val db = mNoteDB?.writableDatabase
        val match = sUriMatcher.match(uri)
        val updated: Int
        Log.d("debug", "update: $uri, match: $match")

        when (match) {
            NOTE_LIST -> updated = db!!.update(NoteContract.Note.TABLE_NAME, values, selection, selectionArgs)
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }

        if (updated > 0) {
            context.contentResolver.notifyChange(uri, null)
        }
        return updated
    }

    override fun delete(uri: Uri?,
                        selection: String?,
                        selectionArgs: Array<out String>?): Int {
        val db = mNoteDB?.writableDatabase
        val match = sUriMatcher.match(uri)
        val deleted: Int
        val customSelection = selection ?: "1"
        Log.d("debug", "delete: $uri, match: $match")

        when (match) {
            NOTE_LIST -> deleted = db!!.delete(NoteContract.Note.TABLE_NAME, customSelection, selectionArgs)
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }

        if (deleted > 0) {
            context.contentResolver.notifyChange(uri, null)
        }

        return deleted
    }

    override fun getType(uri: Uri?): String {
        val match: Int = sUriMatcher.match(uri)
        Log.d("debug", "getType: $uri, match: $match")

        when (match) {
            NOTE_ID -> return NoteContract.Note.CONTENT_ITEM_TYPE
            NOTE_LIST -> return NoteContract.Note.CONTENT_TYPE
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }
    }
}