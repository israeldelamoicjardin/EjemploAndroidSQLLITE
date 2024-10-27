package es.israeldelamo.ejemplosqliteandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.israeldelamo.ejemplosqliteandroid.databinding.ActivityActualizarNotaBinding
import es.israeldelamo.ejemplosqliteandroid.databinding.ActivityMainBinding


/**
 * Clase modificadora de las notas
 */
class ActualizarNotaActivity : AppCompatActivity() {

    /**
     * El enlace a bidning
     */
    private lateinit var binding: ActivityActualizarNotaBinding

    /**
     * objeto de la base de datos
     */
    private lateinit var  db : NotaDatabaseHelper

    /**
     * La id de la nota inicializada a vacio
     */
    private var idNota : Int = -1


    /**
     * Creador de la ventana de modificacion en función del id leido
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        //enlazados mediante binding
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // listo para poder usar las funciones de NotaDatabaseHelper en este contexto, en this context
        db =  NotaDatabaseHelper(this)

        //como viene de un putExtra desde onBindViewHoldser desde NotasAdaptador.kt
        // lo recupero con getIntExtra, ese extra viede desde el putExtra
        idNota = intent.getIntExtra("id_nota", -1)
        if (idNota == -1) {
            finish() //no llego info sobre el numero de nota
            return
        } else {
            val nota = db.getIdNota(idNota)
            //con los datos recuperados rellenamos la pantalla
            binding.etTitulo        .setText(nota.titulo)
            binding.etDescripcion   .setText(nota.descripcion)

            binding.ivActualizarNota.setOnClickListener {
                val tituloNuevo         = binding.etTitulo.text.toString()
                val descripcionNuevo    = binding.etDescripcion.text.toString()

                val notaActualiza = Nota(idNota, tituloNuevo,descripcionNuevo)
                // con el objeto que ha leido los textos nuevos, ya podemos lanzar updatenota
                db.updateNota(notaActualiza)
                //como hemos tenido exito, nos volvemos a la actividad principal
                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "Actualizacion hecha", Toast.LENGTH_SHORT).show()

            }

        }
        db.close()
    }
}