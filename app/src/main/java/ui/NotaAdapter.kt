package com.example.equipo0.ui

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class NotaAdapter(
    private val notas: List<Pair<String, String>>,
    private val onEliminar: (String) -> Unit
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo: TextView = view.findViewById(android.R.id.text1)
        val tvNota: TextView   = view.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val (titulo, nota) = notas[position]
        holder.tvTitulo.text = titulo
        holder.tvTitulo.setTextColor(android.graphics.Color.parseColor("#1B5E20"))
        holder.tvTitulo.textSize = 16f
        holder.tvNota.text = nota
        holder.itemView.setOnLongClickListener {
            onEliminar(titulo)
            true
        }
    }

    override fun getItemCount() = notas.size
}