package com.example.equipo0.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.equipo0.databinding.FragmentDetalleBinding
import com.example.equipo0.model.PlantasData

class DetalleFragment : Fragment() {

    private var _binding: FragmentDetalleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantaId = arguments?.getInt("plantaId") ?: return
        val planta = PlantasData.lista.find { it.id == plantaId } ?: return

        with(binding) {
            tvDetalleNombre.text = planta.nombreComun
            tvDetalleCientifico.text = planta.nombreCientifico
            tvDetalleDescripcion.text = planta.descripcion
            tvDetalleUsos.text = planta.usos
            tvDetallePreparacion.text = planta.preparacion
            tvDetalleContra.text = planta.contraindicaciones
            imgDetalle.load(planta.imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}