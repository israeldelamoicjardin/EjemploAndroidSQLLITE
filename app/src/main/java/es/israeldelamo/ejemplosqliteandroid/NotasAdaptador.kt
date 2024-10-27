package es.israeldelamo.ejemplosqliteandroid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 * Clase intermediario entre las notas y el listado de las mismas y el holder
 * es un holder pues tiene dentro dos campos de texto
 *
 *
 */

class NotasAdaptador (





    /**
     * El listado de notas con el que vamos a trabar
     */
    private  var notas  : List<Nota>,
    context: Context) : RecyclerView.Adapter<NotasAdaptador.NotaViewHolder>() {

        //objeto de gestion de la base de datos
    private val db : NotaDatabaseHelper = NotaDatabaseHelper(context)

    /**
     * CAda vez que se cree el viewHolder se dispara onCreateViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota,parent, false)
        return NotaViewHolder(view)
    }

    /**
     * Rellena el listado
     */
    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        // en cada holder nuevo, de la nota que has leido rellena esos campos
        holder.itemTitulo.text          = nota.titulo
        holder.itemDescripcion.text     = nota.descripcion


        // asociamos el boton de modificar
        holder.ivActualizar.setOnClickListener    {
            val intent = Intent(holder.itemView.context, ActualizarNotaActivity::class.java).apply {
               //pasamos un parametro extra en la anterior actividad
                putExtra("id_nota", nota.id)
            }
            //lanza la actividad y enseña en un mensaje su id
            holder.itemView.context.startActivity(intent)
                Toast.makeText(
                    holder.itemView.context,
                    "El id de la nota seleccionada es ${nota.id}",
                    Toast.LENGTH_LONG
                ).show()
        }


        // asociamos el boton de eliminar
        holder.ivEliminar.setOnClickListener    {

            db.deleteNota(nota.id)

            // enseña en un mensaje su id

                Toast.makeText(
                    holder.itemView.context,
                    "Nota eliminada",
                    Toast.LENGTH_LONG
                ).show()
            //refrescamos con las nuevas notas al reeleer la base de datos
            refrescarLista(db.getAllNotas())
            db.close()
        }


    }

    /**
     * Devuelve la cantidad de elementos de la lista
     */
    override fun getItemCount(): Int {
       return notas.size
    }

    /**
     * Refresca la lista por cada elemento creado
     */
    fun refrescarLista (nuevaNota : List<Nota>) {
        notas = nuevaNota
        //este método avisara del cambio para que se refresque
        notifyDataSetChanged()

    }







    /**
     * Constructor
     */
    class NotaViewHolder ( itemView: View) : RecyclerView.ViewHolder(itemView){
        //este holder contiene precisamente el titulo del item y la descripcion del item
        val itemTitulo      : TextView      = itemView.findViewById(R.id.tv_item_nombre)
        val itemDescripcion : TextView      = itemView.findViewById(R.id.tv_item_descripcion)
        val ivActualizar    : ImageView     = itemView.findViewById(R.id.ivActualizar)
        val ivEliminar      : ImageView     = itemView.findViewById(R.id.ivEliminar)

    }



}