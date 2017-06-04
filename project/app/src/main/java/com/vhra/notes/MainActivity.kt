package com.vhra.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.vhra.notes.data.Note
import com.vhra.notes.data.NoteContract
import com.vhra.notes.data.toContentValues
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var mCount: Int  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }

    fun addListView(view: View) {
        Log.d("debug", "addListView")
        mCount++
        val note = Note(-1, 0, "Title " + mCount, "Note has created by device")
        contentResolver.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
    }

    fun fetchServer(view: View) {
        Log.d("debug", "fetchServer")
        Thread(Runnable {
            val restURL = "http://192.168.25.94:3000/1.0/notes/"
            val connection = abrirConexao(restURL, "GET", false)
            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val jsonString = streamToString(connection.inputStream)
                val json = JSONObject(jsonString)
                val payload = json.getJSONObject("payload")
                val jsonArray = payload.getJSONArray("notes")
                for (i in 0..jsonArray.length()-1) {
                    try {
                        val oneObject = jsonArray.getJSONObject(i)
                        val note = Note(
                                oneObject.getInt("_id"),
                                oneObject.getInt("dbid"),
                                oneObject.getString("title"),
                                oneObject.getString("body")
                        )
                        contentResolver.insert(NoteContract.Note.CONTENT_URI, note.toContentValues())
                    } catch (e: JSONException) {
                        Log.e("debug", e.message)
                    }
                }
            }
        }).start()
    }

    @Throws(IOException::class)
    private fun streamToString(`is`: InputStream): String {
        val bytes = ByteArray(1024)
        val baos = ByteArrayOutputStream()
        var lidos: Int

        lidos = `is`.read(bytes)
        while (lidos > 0) {
            baos.write(bytes, 0, lidos)
            lidos = `is`.read(bytes)
        }
        return String(baos.toByteArray())
    }

    @Throws(Exception::class)
    private fun abrirConexao(url: String, metodo: String, doOutput: Boolean): HttpURLConnection {
        val urlCon = URL(url)
        val conexao = urlCon.openConnection() as HttpURLConnection
        conexao.setReadTimeout(15000)
        conexao.setConnectTimeout(15000)
        conexao.setRequestMethod(metodo)
        conexao.setDoInput(true)
        conexao.setDoOutput(doOutput)
        if (doOutput) {
            conexao.addRequestProperty("Content-Type", "application/json")
        }
        conexao.connect()
        return conexao
    }
}
