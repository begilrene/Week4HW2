package com.example.week4hw2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE IF NOT EXISTS" + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT, "
                + LAST_NAME_COL + " TEXT, "
                + COMPANY_COL + " TEXT, "
                + ADDRESS_COL + " TEXT, "
                + CITY_COL + " TEXT, "
                + COUNTRY_COL + " TEXT, "
                + STATE_COL + " TEXT, "
                + ZIP_COL + " TEXT, "
                + PRIMARY_PHONE_COL + " TEXT, "
                + SECONDARY_PHONE_COL + " TEXT, "
                + EMAIL_COL + " TEXT" + ")")
        Log.i("QUINTRIX query",query)
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addEntry(name: String, last_name : String,
                    company: String, address: String,
                    city: String, country: String,
                    state: String, zip: String,
                    primary_phone: String, secondary_phone: String,
                    email: String){
        val values = ContentValues()

        values.put(FIRST_NAME_COL, name)
        values.put(LAST_NAME_COL, last_name)
        values.put(COMPANY_COL, company)
        values.put(ADDRESS_COL, address)
        values.put(CITY_COL, city)
        values.put(COUNTRY_COL, country)
        values.put(STATE_COL, state)
        values.put(ZIP_COL, zip)
        values.put(PRIMARY_PHONE_COL, primary_phone)
        values.put(SECONDARY_PHONE_COL, secondary_phone)
        values.put(EMAIL_COL, email)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    fun getPerson(ide: Int): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE id == $ide", null)
    }

    companion object{

        private val DATABASE_NAME = "ANDROIDKTCLASS"
        private val DATABASE_VERSION = 3
        val TABLE_NAME = "qt_table"
        val ID_COL = "id"
        val FIRST_NAME_COL = "first_name"
        val LAST_NAME_COL = "last_name"
        val COMPANY_COL = "company"
        val ADDRESS_COL = "address"
        val CITY_COL = "city"
        val COUNTRY_COL = "country"
        val STATE_COL = "state"
        val ZIP_COL = "zip"
        val PRIMARY_PHONE_COL = "primary_phone"
        val SECONDARY_PHONE_COL = "secondary_phone"
        val EMAIL_COL = "email"
        val AGE_COL = "age"

    }
}