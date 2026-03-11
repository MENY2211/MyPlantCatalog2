package com.example.equipo0.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.equipo0.R
import com.example.equipo0.databinding.FragmentExplorarBinding
import com.example.equipo0.model.Planta
import com.example.equipo0.model.PlantasData

class ExplorarFragment : Fragment() {

    private var _binding: FragmentExplorarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplorarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlantaAdapter(PlantasData.lista) { planta ->
            val bundle = Bundle().apply { putInt("plantaId", planta.id) }
            findNavController().navigate(R.id.detalleFragment, bundle)
        }

        binding.recyclerExplorar.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerExplorar.adapter = adapter

        binding.etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                val filtradas = filtrar(query)
                adapter.actualizar(filtradas)
                binding.tvSinResultados.visibility =
                    if (filtradas.isEmpty()) View.VISIBLE else View.GONE
            }
        })
    }

    private fun filtrar(query: String): List<Planta> {
        if (query.isEmpty()) return PlantasData.lista
        return PlantasData.lista.filter { planta ->
            planta.nombreComun.lowercase().contains(query) ||
                    planta.nombreCientifico.lowercase().contains(query) ||
                    planta.usos.lowercase().contains(query) ||
                    planta.descripcion.lowercase().contains(query)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}