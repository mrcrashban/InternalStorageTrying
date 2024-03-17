package com.example.internalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOError
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var field: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fileName = "example3.txt"
        val fileContent = "Hello, kirill!"
        saveTextFileToInternalStorage (applicationContext, fileName, fileContent)
        val fileContentOut = loadTextFileFromInternalStorage(applicationContext, fileName)
        field = findViewById(R.id.pipipopo)
        field.text = fileContentOut
    }

    private fun saveTextFileToInternalStorage(context: Context, filename: String, fileContent: String):Boolean {
        var fos: FileOutputStream? = null
        return try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE)
            fos.write(fileContent.toByteArray())
            true
        } catch (e: IOException){
            e.printStackTrace()
            false
        } finally {
            fos?.close()
        }
    }

    private fun loadTextFileFromInternalStorage(context: Context, fileName: String): String {
        val stringBuilder = StringBuilder()
        try {
            val fis: FileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fis)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            fis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}

