package com.example.equipo0.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.equipo0.R
import com.example.equipo0.databinding.FragmentCatalogoBinding
import com.example.equipo0.model.PlantasData

class CatalogoFragment : Fragment() {

    private var _binding: FragmentCatalogoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlantaAdapter(PlantasData.lista) { planta ->
            val bundle = Bundle().apply { putInt("plantaId", planta.id) }
            findNavController().navigate(R.id.action_catalogo_to_detalle, bundle)
        }

        binding.recyclerPlantas.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPlantas.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}