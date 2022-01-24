package com.makentoshe.androidgithubcitemplate.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class My_db_helper (context: Context): SQLiteOpenHelper(context, Marker.DATABASE_NAME, null, Marker.DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Marker.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Marker.SQL_DELETE_TABLE)
        onCreate(db)
    }

}