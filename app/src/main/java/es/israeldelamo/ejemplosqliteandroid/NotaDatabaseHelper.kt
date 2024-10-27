package es.israeldelamo.ejemplosqliteandroid

import android.content.ContentValues
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
        private const val   COLUMN_TITLE = "titulo"
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

    /**
     * Modifica la tabla de la base de datos
     */

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery =
            "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        // ya hemos mirado si existia o no, ahora la podemos crear sin que de errores
        onCreate(db)
    }

    /**
     * Pasado un objeto nota, la añade a la base de datos
     * @param nota la nota a añadir
     */

    fun insertNota(nota: Nota){
        //la abro en modo escritura
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,       nota.titulo)
            put(COLUMN_DESCRIPCION, nota.descripcion)
            //en este caso ID es autonumérico
        }
        //insertamos datos
        db.insert(TABLE_NAME, null, values)
        //y ahora a cerrar
        db.close()
    }

}