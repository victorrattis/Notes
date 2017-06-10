package com.vhra.notes.data

class TagNoteSchema {
    companion object {
        val TABLE_NAME = "tag_note"
        val NOTE_ID = "note_id"
        val DATABASE_ID = "dbid"
        val TAG_ID = "tag_id"
        val ID = "_id"

        val TABLE_CREATE = """
            CREATE TABLE $TABLE_NAME(
                $NOTE_ID INTEGER,
                $DATABASE_ID INTEGER,
                $TAG_ID INTEGER,
                FOREIGN KEY($NOTE_ID) REFERENCES notes($ID),
                FOREIGN KEY($DATABASE_ID) REFERENCES notes($DATABASE_ID),
                FOREIGN KEY($TAG_ID) REFERENCES tags($ID)
            );
            """
    }
}