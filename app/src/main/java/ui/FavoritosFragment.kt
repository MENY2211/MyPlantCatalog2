package com.example.equipo0.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.equipo0.R
import com.example.equipo0.databinding.FragmentFavoritosBinding
import com.example.equipo0.model.PlantasData
import com.example.equipo0.util.FavoritosManager

class FavoritosFragment : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarFavoritos()
    }

    override fun onResume() {
        super.onResume()
        cargarFavoritos()
    }

    private fun cargarFavoritos() {
        val ids = FavoritosManager.obtenerFavoritos(requireContext())
        val favoritas = PlantasData.lista.filter { it.id in ids }

        if (favoritas.isEmpty()) {
            binding.recyclerFavoritos.visibility = View.GONE
            binding.tvFavoritosVacio.visibility = View.VISIBLE
        } else {
            binding.tvFavoritosVacio.visibility = View.GONE
            binding.recyclerFavoritos.visibility = View.VISIBLE
            val adapter = PlantaAdapter(favoritas) { planta ->
                val bundle = Bundle().apply { putInt("plantaId", planta.id) }
                findNavController().navigate(R.id.detalleFragment, bundle)
            }
            binding.recyclerFavoritos.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerFavoritos.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}