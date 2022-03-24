package com.example.week4hw2

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.week4hw2.databinding.ActivityMainBinding
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("Range")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE), 24)
        Log.d("XD", "Reading files into DB...")
        try {
            val db = DBHelper(this, null)
            Log.d("XD", "1")
            val csvfile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "100-contacts.csv")
            val reader = BufferedReader(FileReader(csvfile))
            Log.d("XD", "2")
            reader.readLines().forEach{
                val items = it.split(",")
                db.addEntry(items[0],items[1],items[2],items[3],items[4],items[5],items[6],
                            items[7],items[8],items[9],items[10])
                Log.d("XD", items.toString())

                Toast.makeText(this, ":)", Toast.LENGTH_LONG).show()
            }

        } catch(e: Exception){
            Toast.makeText(this, e.stackTraceToString() ,Toast.LENGTH_LONG).show()
        }

        binding.button.setOnClickListener{
            val db = DBHelper(this, null)
            val id = binding.editTextTextPersonName.text.toString()
            if (id.toInt() < 1 || id.toInt() > 99){
                Toast.makeText(this, "Invalid number. Please enter a number between 1 and 99", Toast.LENGTH_LONG).show()
                binding.editTextTextPersonName.setText("")
            }
            else {
                val cursor = db.getPerson(id.toInt())
                cursor!!.moveToFirst()
                if (cursor != null) {

                    binding.textView.setText(
                        cursor.getString(cursor.getColumnIndex(DBHelper.FIRST_NAME_COL)) + " " +
                                cursor.getString(cursor.getColumnIndex(DBHelper.LAST_NAME_COL)) + " " +
                                cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_COL))
                    )
                }
            }


        }
    }

    fun queryDB(view: View) {

    }
}