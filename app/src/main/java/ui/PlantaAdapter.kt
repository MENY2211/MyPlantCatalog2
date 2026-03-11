package com.example.equipo0.ui

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.equipo0.databinding.ItemPlantaBinding
import com.example.equipo0.model.Planta
import com.example.equipo0.util.FavoritosManager

class PlantaAdapter(
    private var plantas: List<Planta>,
    private val onItemClick: (Planta) -> Unit
) : RecyclerView.Adapter<PlantaAdapter.PlantaViewHolder>() {

    inner class PlantaViewHolder(val binding: ItemPlantaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlantaViewHolder(ItemPlantaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PlantaViewHolder, position: Int) {
        val planta = plantas[position]
        val context = holder.itemView.context
        with(holder.binding) {
            tvNombreComun.text = planta.nombreComun
            tvNombreCientifico.text = planta.nombreCientifico
            tvUsos.text = planta.usos
            imgPlanta.setImageResource(planta.imageRes)

            // Favorito
            val esFav = FavoritosManager.esFavorito(context, planta.id)
            btnFavorito.setImageResource(
                if (esFav) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )
            btnFavorito.setOnClickListener {
                val nuevo = FavoritosManager.toggleFavorito(context, planta.id)
                btnFavorito.setImageResource(
                    if (nuevo) android.R.drawable.btn_star_big_on
                    else android.R.drawable.btn_star_big_off
                )
            }

            chipVer.setOnClickListener { onItemClick(planta) }
            root.setOnClickListener { onItemClick(planta) }
        }
    }

    override fun getItemCount() = plantas.size

    fun actualizar(nuevaLista: List<Planta>) {
        plantas = nuevaLista
        notifyDataSetChanged()
    }
}