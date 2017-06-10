package com.vhra.notes.data

class NoteSchema {
    companion object {
        val ID = "_id"
        val TABLE_NAME = "notes"
        val DATABASE_ID = "dbid"
        val TITLE = "title"
        val BODY = "body"

        val TABLE_CREATE = """
            CREATE TABLE $TABLE_NAME (
                $ID INTEGER,
                $DATABASE_ID INTEGER NOT NULL,
                $TITLE TEXT,
                $BODY TEXT,
                PRIMARY KEY ($ID, $DATABASE_ID)
            );
            """
    }
}