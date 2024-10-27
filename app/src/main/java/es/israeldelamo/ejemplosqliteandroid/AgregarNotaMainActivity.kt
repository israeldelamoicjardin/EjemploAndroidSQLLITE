package es.israeldelamo.ejemplosqliteandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.israeldelamo.ejemplosqliteandroid.databinding.ActivityAgregarNotaMainBinding
import es.israeldelamo.ejemplosqliteandroid.databinding.ActivityMainBinding

/**
 * En esta clase llamamos al gestor de base de datos
 * leemos los campos
 * insertamos y modificamos a placer
 */

class AgregarNotaMainActivity : AppCompatActivity() {

    //como siempre usamos el binding, debe coincidir con la declaración de activity que hayamos hecho
    /**
     * binding para los datos con lo grafico
     */
    private lateinit var binding: ActivityAgregarNotaMainBinding

    /**
     * conexion a la base de datos
     */
    private lateinit var db : NotaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        //nuestras tres lineas favoritas para hacer binding
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarNotaMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //comenzamos con la db
        db = NotaDatabaseHelper(this )

        //enlazamos el boton de chekc con la base de datos
        binding.ivGuardarNota.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            //crepo el objeto nota
            val nota = Nota(0,titulo,descripcion)
            //lo inserto
            db.insertNota(nota)
            //cierro esta activity y abro la main
            startActivity(Intent(applicationContext, MainActivity::class.java))
            //limpiamos la pila
            finishAffinity()
            Toast.makeText(applicationContext, "Se ha agreagado la nota", Toast.LENGTH_LONG).show()


        }

    }
}