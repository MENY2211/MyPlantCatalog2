package com.example.equipo0.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
            Toast.makeText(requireContext(), planta.nombreComun, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerPlantas.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPlantas.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}