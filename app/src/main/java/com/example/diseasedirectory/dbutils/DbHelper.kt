package com.example.diseasedirectory.dbutils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DbHelper(val context: Context) {

    companion object {
        val dbName = "DiseasesDb"
    }

    val database: SQLiteDatabase

    init {
        database = open()
    }

    private fun open(): SQLiteDatabase {
        val dbFile = context.getDatabasePath("$dbName.db")
        if (!dbFile.exists()) {
            try {
                val checkDB = context.openOrCreateDatabase("$dbName.db", Context.MODE_PRIVATE, null)
                checkDB.close()
                copyDatabase(dbFile)
            } catch (e: IOException) {
                throw RuntimeException("Error opening db")
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun copyDatabase(dbFile: File) {
        val iss = context.assets.open("$dbName.db")
        val os = FileOutputStream(dbFile)

        val buffer = ByteArray(1024)
        generateSequence{ val i = iss.read(buffer); if (i < 0) null else i }
            .forEach { i ->
                os.write(buffer, 0, i)
            }
        os.flush()
        os.close()
        iss.close()
    }

    fun close() {
        database.close()
    }
}
