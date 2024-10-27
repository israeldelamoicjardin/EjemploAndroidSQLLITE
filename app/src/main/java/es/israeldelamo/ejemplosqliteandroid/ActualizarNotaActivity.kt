package es.israeldelamo.ejemplosqliteandroid

import android.os.Bundle
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

    private lateinit var binding: ActivityActualizarNotaBinding


    /**
     * Creador de la ventana
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        //enlazados mediante binding
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarNotaBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}