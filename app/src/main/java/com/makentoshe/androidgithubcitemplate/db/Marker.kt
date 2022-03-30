package com.makentoshe.androidgithubcitemplate.db
import android.provider.BaseColumns
object Marker:BaseColumns {
    const val TABLE_NAME = "Markercoords"
    const val COLUMN_NAME_TITLE1 = "numer"
    const val COLUMN_NAME_TITLE2 = "mlatitude"
    const val COLUMN_NAME_TITLE3 = "mlongitude"



    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Marker.db"


    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE1 TEXT," +
            "$COLUMN_NAME_TITLE2 TEXT, $COLUMN_NAME_TITLE3 TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"


}