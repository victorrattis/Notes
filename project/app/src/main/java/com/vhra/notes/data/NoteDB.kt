package com.vhra.notes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDB(context: Context?) :
        SQLiteOpenHelper(context, NoteDB.DATABASE_NAME, null, NoteDB.DATABASE_VERSION) {

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

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("""
             CREATE TABLE auto_increment (
                $DATABASE_ID INT,
                count INT
            );""")

        db?.execSQL("""
            CREATE TABLE $NOTES_TABLE_NAME (
                $ID INTEGER,
                $DATABASE_ID INTEGER NOT NULL,
                $TITLE TEXT,
                $BODY TEXT,
                PRIMARY KEY ($ID, $DATABASE_ID)
            ); """)

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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE_NAME IF EXISTS $NOTES_TABLE_NAME")
        onCreate(db)
    }
}