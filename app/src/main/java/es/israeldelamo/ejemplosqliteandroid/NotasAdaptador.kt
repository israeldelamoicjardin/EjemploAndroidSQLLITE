package es.israeldelamo.ejemplosqliteandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Clase intermediario entre las notas y el listado de las mismas y el holder
 * es un holder pues tiene dentro dos campos de texto
 *
 *
 */

class NotasAdaptador (
    private  var notas  : List<Nota>,
    context: Context) : RecyclerView.Adapter<NotasAdaptador.NotaViewHolder>() {


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
        val itemTitulo : TextView = itemView.findViewById(R.id.tv_item_nombre)
        val itemDescripcion : TextView = itemView.findViewById(R.id.tv_item_descripcion)

    }



}