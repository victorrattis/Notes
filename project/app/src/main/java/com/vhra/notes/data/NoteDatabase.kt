package com.vhra.notes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabase(context: Context?) :
        SQLiteOpenHelper(context, NoteDatabase.DATABASE_NAME, null, NoteDatabase.DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "note.db"
        val DATABASE_VERSION = 1
    }

    val DATABASE_ID = NoteContract.Note.Key.DATABASE_ID
    val NOTES_DATABASE_ID = NoteContract.Note.NOTES_DATABASE_ID
    val NOTES_TABLE_NAME = NoteContract.Note.TABLE_NAME
    val ID = NoteContract.Note.Key._ID
    val TITLE = NoteContract.Note.Key.TITLE
    val BODY = NoteContract.Note.Key.BODY

    val TAGS_TABLE_NAME = NoteContract.TAG.TABLE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""
             CREATE TABLE auto_increment (
                $DATABASE_ID INT,
                count INT
            );""")

        db?.execSQL(NoteSchema.TABLE_CREATE)

        db?.execSQL("""
             INSERT INTO auto_increment VALUES ($NOTES_DATABASE_ID, 0); """)

        db?.execSQL("""
            CREATE TRIGGER pkInsert AFTER INSERT ON $NOTES_TABLE_NAME
            WHEN (new.$ID IS NULL)
            BEGIN
                UPDATE auto_increment
                    SET count = count + 1
                    WHERE $DATABASE_ID = 0;

                UPDATE $NOTES_TABLE_NAME
                    SET $ID = (
                        SELECT count
                        FROM auto_increment
                        WHERE $DATABASE_ID = 0)
                    WHERE ROWID = new.ROWID;
            END; """)

        db?.execSQL(TagSchema.TABLE_CREATE)
        db?.execSQL(TagNoteSchema.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE_NAME IF EXISTS $NOTES_TABLE_NAME")
        onCreate(db)
    }
}