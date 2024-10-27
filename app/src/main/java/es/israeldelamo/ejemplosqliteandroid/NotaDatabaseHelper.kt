package es.israeldelamo.ejemplosqliteandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Clase de gestión de la base de datos
 */

class NotaDatabaseHelper (context: Context) : SQLiteOpenHelper(
        context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        private const val   DATABASE_NAME = "notas.db"
        private const val   DATABASE_VERSION = 1
        private const val   TABLE_NAME = "notas"
        // y ahora las columnas o atributos
        private const val   COLUMN_ID = "id"
        private const val   COLUMN_TITLE = "id"
        private const val   COLUMN_DESCRIPCION = "descripcion"

    }

    /**
     * Creadora de la tabla
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY, " +
                    "$COLUMN_TITLE TEXT, " +
                    "$COLUMN_DESCRIPCION TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}