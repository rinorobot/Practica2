package com.example.prctica2.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_CARS (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, modelo TEXT NOT NULL, marca TEXT NOT NULL, precio TEXT NOT NULL )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_CARS")
        onCreate(p0)
    }


    companion object{
        private  const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "db.cars"
        const val TABLE_CARS = "cars"
    }

}