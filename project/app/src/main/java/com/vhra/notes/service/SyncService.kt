package com.vhra.notes.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.vhra.notes.processor.SyncDataProcessor
import com.vhra.notes.webservice.Executor
import com.vhra.notes.webservice.RestExecutor

class SyncService : IntentService(TAG) {
    companion object {
        val TAG = "SyncService"
        val SYNC_DATA_ACTION: String = "com.vhra.notes.action.SYNC_DATA"
    }

    private var mExecutor: Executor? = null

    override fun onCreate() {
        super.onCreate()
        mExecutor = RestExecutor(applicationContext)
    }

    override fun onHandleIntent(intent: Intent?) {
        val action: String? = intent?.action
        Log.d("debug", "SyncService: $action")

        if (SYNC_DATA_ACTION == action) {
            val syncDataProcess: SyncDataProcessor = SyncDataProcessor(contentResolver)
            mExecutor?.execute("http://192.168.25.94:3000/1.0/notes", syncDataProcess)
        }
    }
}