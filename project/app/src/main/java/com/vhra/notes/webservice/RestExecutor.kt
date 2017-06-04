package com.vhra.notes.webservice

import android.content.ContentResolver
import android.content.Context
import android.util.Log
import com.vhra.notes.processor.Processor
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.URL

class RestExecutor(context: Context?) : Executor {
    private val TAG = "RestExecutor"

    private var mContext: Context? = context

    override fun execute(url: String, processor: Processor) {
        try {
            val connection = this.openConnection(url, "GET", false)
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response: String? = this.streamToString(connection.inputStream)
                processor.process(response)
            }
        } catch (e: ConnectException) {
            Log.i(TAG, e.message)
        }
        // TODO: retry later
    }

    @Throws(Exception::class)
    fun openConnection(url: String, method: String, doOutput: Boolean): HttpURLConnection {
        val urlCon = URL(url)
        val connection = urlCon.openConnection() as HttpURLConnection
        connection.readTimeout = 15000
        connection.connectTimeout = 15000
        connection.requestMethod = method
        connection.doInput = true
        connection.doOutput = doOutput
        if (doOutput) {
            connection.addRequestProperty("Content-Type", "application/json")
        }

        connection.connect()
        return connection
    }

    @Throws(IOException::class)
    private fun streamToString(inputStream: InputStream): String {
        val bytes = ByteArray(1024)
        val buffer = ByteArrayOutputStream()
        var lines: Int = inputStream.read(bytes)
        while (lines > 0) {
            buffer.write(bytes, 0, lines)
            lines = inputStream.read(bytes)
        }
        return String(buffer.toByteArray())
    }
}
