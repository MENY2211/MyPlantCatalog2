package com.example.equipo0.ui

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo0.R
import com.example.equipo0.databinding.ItemNotaBinding
import com.example.equipo0.model.PlantasData
import java.text.SimpleDateFormat
import java.util.*

class NotaAdapter(
    private val notas: List<Pair<String, String>>,
    private val onEliminar: (String) -> Unit,
    private val onLeerMas: (String, String) -> Unit
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(val binding: ItemNotaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotaViewHolder(ItemNotaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val (titulo, nota) = notas[position]
        with(holder.binding) {

            tvNotaTitulo.text = titulo
            tvNotaPreview.text = nota

            // Fecha actual como placeholder
            val fecha = SimpleDateFormat("dd MMM yyyy", Locale("es")).format(Date())
            tvNotaFecha.text = fecha.uppercase()

            // Imagen: busca si hay planta con ese nombre, si no usa la primera
            val planta = PlantasData.lista.find {
                titulo.lowercase().contains(it.nombreComun.lowercase()) ||
                        it.nombreComun.lowercase().contains(titulo.lowercase())
            } ?: PlantasData.lista.first()
            imgNotaPlanta.setImageResource(planta.imageRes)

            // Chip categoría basado en usos
            val categoria = when {
                nota.lowercase().contains("digest") || nota.lowercase().contains("estómago") -> "DIGESTIVO"
                nota.lowercase().contains("piel") || nota.lowercase().contains("herida") -> "PIEL"
                nota.lowercase().contains("nervio") || nota.lowercase().contains("estrés") -> "RELAJANTE"
                nota.lowercase().contains("tos") || nota.lowercase().contains("respir") -> "RESPIRATORIO"
                else -> "GENERAL"
            }
            chipCategoria.text = categoria

            tvLeerMas.setOnClickListener { onLeerMas(titulo, nota) }
            root.setOnLongClickListener { onEliminar(titulo); true }
        }
    }

    override fun getItemCount() = notas.size
}