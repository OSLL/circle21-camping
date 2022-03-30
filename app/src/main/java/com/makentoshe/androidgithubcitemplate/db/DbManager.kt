package com.makentoshe.androidgithubcitemplate.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class DbManager( context: Context) {
    val myDbHelper = My_db_helper(context)
    var db: SQLiteDatabase? = null
    fun closeDb(){
        myDbHelper.close()
    }
    fun openDb(){
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content1: String, content2: String){
        val values = ContentValues().apply{
            put(Marker.COLUMN_NAME_TITLE1, title)
            put(Marker.COLUMN_NAME_TITLE2, content1)
            put(Marker.COLUMN_NAME_TITLE3, content2)
        }
        db?.insert(Marker.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun ReadDbData1(): ArrayList<String>{
        val datalist1 = ArrayList<String>()
        val cursor = db?.query(Marker.TABLE_NAME, null, null, null,
            null, null, null)

            while(cursor?.moveToNext()!!){
                val datatext = cursor.getString(cursor.getColumnIndex(Marker.COLUMN_NAME_TITLE2))
                datalist1.add(datatext.toString())
            }
        cursor.close()

        return datalist1
    }
    @SuppressLint("Range")
    fun ReadDbData2(): ArrayList<String>{
        val datalist2 = ArrayList<String>()

        val cursor = db?.query(Marker.TABLE_NAME, null, null, null,
            null, null, null)

        while(cursor?.moveToNext()!!){
            val datatext = cursor.getString(cursor.getColumnIndex(Marker.COLUMN_NAME_TITLE3))
            datalist2.add(datatext.toString())
        }
        cursor.close()

        return datalist2
    }
    fun removeFromDb(id: String){
        val selec = BaseColumns._ID + "=$id"
        db?.delete(Marker.TABLE_NAME, selec, null)
    }


}
