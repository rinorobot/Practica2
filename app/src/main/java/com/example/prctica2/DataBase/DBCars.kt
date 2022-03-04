package com.example.prctica2.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.prctica2.Models.Car
import java.lang.Exception

class DBCars(context: Context?) : DBHelper(context) {

    val context = context

    fun insertGame(nombre: String, modelo: String, marca: String, precio: String): Long{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0
        try {
            val values = ContentValues()
            values.put("nombre",nombre)
            values.put("modelo",modelo)
            values.put("marca",marca)
            values.put("precio",precio)

            id = db.insert(TABLE_CARS,null,values)

        }catch (e: Exception){

        }finally {
            db.close()
        }
        return id
    }

    fun getCars(): ArrayList<Car>{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var listOfCar = ArrayList<Car>()
        var carTemp: Car? = null
        var cursorCar: Cursor? = null

        cursorCar = db.rawQuery("SELECT * FROM $TABLE_CARS",null)

        if (cursorCar.moveToFirst()){
            do {
                carTemp = Car(cursorCar.getInt(0),cursorCar.getString(1),cursorCar.getString(2),cursorCar.getString(3),cursorCar.getString(4))
                listOfCar.add(carTemp)
            }while (cursorCar.moveToNext())
        }
        cursorCar.close()

        return listOfCar
    }
    fun getCar(id: Int): Car?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var car: Car? = null
        var cursorCars: Cursor? = null

        cursorCars = db.rawQuery("SELECT * FROM $TABLE_CARS WHERE id = $id LIMIT 1", null)

        if (cursorCars.moveToFirst()){
           car = Car(cursorCars.getInt(0),cursorCars.getString(1),cursorCars.getString(2),cursorCars.getString(3),cursorCars.getString(4))
        }
        cursorCars.close()

        return car
    }

    fun updateCar(id: Int, nombre: String, marca: String,modelo: String,precio: String): Boolean{
        var flagOK = false
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("UPDATE $TABLE_CARS SET nombre = '$nombre', modelo = '$modelo', marca = '$marca', precio = '$precio' WHERE id = $id")
            flagOK = true
        }catch (e: Exception){

        }finally {
            db.close()
        }

        return flagOK


    }

    fun deleteCar(id: Int): Boolean{
        var flagOk = false

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_CARS WHERE id = $id")
            flagOk = true
        }catch (e: Exception){

        }finally {
            db.close()
        }
        return flagOk
    }
}