package com.example.equipo0.ui

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo0.databinding.ItemPlantaBinding
import com.example.equipo0.model.Planta

class PlantaAdapter(
    private val plantas: List<Planta>,
    private val onItemClick: (Planta) -> Unit
) : RecyclerView.Adapter<PlantaAdapter.PlantaViewHolder>() {

    inner class PlantaViewHolder(val binding: ItemPlantaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlantaViewHolder(ItemPlantaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PlantaViewHolder, position: Int) {
        val planta = plantas[position]
        with(holder.binding) {
            tvNombreComun.text = planta.nombreComun
            tvNombreCientifico.text = planta.nombreCientifico
            tvUsos.text = planta.usos
            imgPlanta.setImageResource(planta.imageRes)  // ← cambio aquí
            chipVer.setOnClickListener { onItemClick(planta) }
            root.setOnClickListener { onItemClick(planta) }
        }
    }

    override fun getItemCount() = plantas.size
}