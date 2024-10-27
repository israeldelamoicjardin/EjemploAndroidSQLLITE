package es.israeldelamo.ejemplosqliteandroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.israeldelamo.ejemplosqliteandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * Binding de la escena con los elementos visuales
     */
    private lateinit var binding : ActivityMainBinding


    /**
     * el enlace con la base de datos, la necesitaremos para rellenar el comienzo
     */
    private lateinit var db : NotaDatabaseHelper

    /**
     * El listado de las notas cogidasd del adaptador
     */
    private lateinit var  notasAdaptador: NotasAdaptador


    override fun onCreate(savedInstanceState: Bundle?) {
        //Con tres lineas asocio mediante el binding lo grafico y su programacion
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicializar la base de datos
        db = NotaDatabaseHelper(this)

        // las notas leidas desde esa base de datos
        notasAdaptador = NotasAdaptador(db.getAllNotas(), this)

        //que el notasRV se vea como un linear
        binding.notasRV.layoutManager = LinearLayoutManager(this)
        binding.notasRV.adapter = notasAdaptador

        //ahora podemos hacer referncia al botón mediante el binding y monstar un mensajin
        binding.FABAgregarNota.setOnClickListener{
           /* Toast.makeText(applicationContext,
                "Botón pulsado",
                Toast.LENGTH_SHORT).show()*/

        // con el doble dos puntos devolvemos la clase "como si fuera" o "as"
        startActivity(Intent(applicationContext, AgregarNotaMainActivity::class.java))

        }
    }

    /**
     * Cuando vuelva del otro activity debe refrescarse
     */
    override fun onResume() {
        super.onResume()
        notasAdaptador.refrescarLista(db.getAllNotas())
    }

}