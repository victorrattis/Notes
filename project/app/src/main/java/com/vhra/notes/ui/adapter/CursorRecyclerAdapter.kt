package com.vhra.notes.ui.adapter

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder

// TODO: Refactor this class
abstract class CursorRecyclerAdapter<T : ViewHolder>(cursor: Cursor?)
        : RecyclerView.Adapter<T>() {

    protected var mDataValid: Boolean = false
    private var mCursor: Cursor? = null
    protected var mRowIDColumn: Int = 0

    init {
        init(cursor)
    }

    internal fun init(cursor: Cursor?) {
        val cursorPresent = cursor != null
        mCursor = cursor
        mDataValid = cursorPresent
        mRowIDColumn = if (cursorPresent) cursor!!.getColumnIndexOrThrow("_id") else -1
        setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        if (!mDataValid) {
            throw IllegalStateException("this should only be called when the cursor is valid")
        }
        if (!mCursor!!.moveToPosition(position)) {
            throw IllegalStateException("couldn't move cursor to position " + position)
        }

        onBindViewHolder(holder, mCursor)
    }

    abstract fun onBindViewHolder(holder: T, cursor: Cursor?)

    override fun getItemCount(): Int {
        if (mDataValid && mCursor != null) {
            return mCursor!!.count
        } else {
            return 0
        }
    }

    override fun getItemId(position: Int): Long {
        if (hasStableIds() && mDataValid && mCursor != null) {
            if (mCursor!!.moveToPosition(position)) {
                return mCursor!!.getLong(mRowIDColumn)
            } else {
                return RecyclerView.NO_ID
            }
        } else {
            return RecyclerView.NO_ID
        }
    }

    /**
     * Change the underlying cursor to a new cursor. If there is an existing cursor it will be
     * closed.

     * @param cursor The new cursor to be used
     */
    fun changeCursor(cursor: Cursor?) {
        val old = swapCursor(cursor)
        old?.close()
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.  Unlike
     * [.changeCursor], the returned old Cursor is *not*
     * closed.

     * @param newCursor The new cursor to be used.
     * *
     * @return Returns the previously set Cursor, or null if there wasa not one.
     * * If the given new Cursor is the same instance is the previously set
     * * Cursor, null is also returned.
     */
    fun swapCursor(newCursor: Cursor?): Cursor? {
        if (newCursor === mCursor) {
            return null
        }
        val oldCursor = mCursor
        mCursor = newCursor
        if (newCursor != null) {
            mRowIDColumn = newCursor.getColumnIndexOrThrow("_id")
            mDataValid = true
            // notify the observers about the new cursor
            notifyDataSetChanged()
        } else {
            mRowIDColumn = -1
            mDataValid = false
            // notify the observers about the lack of a data set
            notifyItemRangeRemoved(0, itemCount)
        }
        return oldCursor
    }

    /**
     *
     * Converts the cursor into a CharSequence. Subclasses should override this
     * method to convert their results. The default implementation returns an
     * empty String for null values or the default String representation of
     * the value.

     * @param cursor the cursor to convert to a CharSequence
     * *
     * @return a CharSequence representing the value
     */
    fun convertToString(cursor: Cursor?): CharSequence {
        return cursor?.toString() ?: ""
    }
}