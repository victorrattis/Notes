package com.vhra.notes.data

interface TagSchema {
    companion object {
        val ID = "_id"
        val TABLE_NAME = "tags"
        val TAG_NAME = "name"

        val COLUMNS = arrayOf(ID, TAG_NAME)

        val TABLE_CREATE = """
            CREATE TABLE $TABLE_NAME(
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $TAG_NAME TEXT UNIQUE NOT NULL
            );
            """
    }
}