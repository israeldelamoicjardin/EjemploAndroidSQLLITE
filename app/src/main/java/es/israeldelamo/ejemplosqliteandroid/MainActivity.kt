package es.israeldelamo.ejemplosqliteandroid

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.israeldelamo.ejemplosqliteandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * Binding de la escena con los elementos visuales
     */
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //Con tres lineas asocio mediante el binding lo grafico y su programacion
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ahora podemos hacer referncia al botón mediante el binding y monstar un mensajin
        binding.FABAgregarNota.setOnClickListener{
            Toast.makeText(applicationContext,
                "Botón pulsado",
                Toast.LENGTH_SHORT).show()
        }
    }
}