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

    /**
     * Lee la base de datos y rellena una List de tipo Nota
     */

    fun getAllNotas() : List<Nota> {
        //creo una lista mutable para poder cambiar cosas
        val listaNotas = mutableListOf<Nota>()

        //la abro en modo lectura
        val db = readableDatabase

        val query = "SELECT * FROM $TABLE_NAME"

        //lanza un cursor
        val cursor = db.rawQuery(query, null)

        //itera mientras que exista otro
        while (cursor.moveToNext()){
            val id =            cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val titulo =        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val descripcion =   cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION))
            //creamos un objeto temporal de tipo Nota
            val nota =  Nota(id, titulo, descripcion)
            //añadimos la nota
            listaNotas.add(nota)
        }
        //cerrar las conexiones
        cursor.close()
        db.close()

        return listaNotas
    }

}