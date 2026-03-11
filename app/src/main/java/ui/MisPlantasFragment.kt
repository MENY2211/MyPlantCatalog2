package com.example.equipo0.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.equipo0.databinding.FragmentMisPlantasBinding
import com.example.equipo0.util.NotasManager

class MisPlantasFragment : Fragment() {

    private var _binding: FragmentMisPlantasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMisPlantasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarNotas()

        binding.fabAgregarNota.setOnClickListener {
            mostrarDialogoNuevaNota()
        }
    }

    override fun onResume() {
        super.onResume()
        cargarNotas()
    }

    private fun cargarNotas() {
        val notas = NotasManager.obtenerNotas(requireContext())

        if (notas.isEmpty()) {
            binding.recyclerMisPlantas.visibility = View.GONE
            binding.tvMisPlantasVacio.visibility = View.VISIBLE
        } else {
            binding.tvMisPlantasVacio.visibility = View.GONE
            binding.recyclerMisPlantas.visibility = View.VISIBLE

            val adapter = NotaAdapter(
                notas.toList(),
                onEliminar = { titulo -> confirmarEliminar(titulo) },
                onLeerMas  = { titulo, texto -> mostrarNotaCompleta(titulo, texto) }
            )
            binding.recyclerMisPlantas.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerMisPlantas.adapter = adapter
        }
    }

    private fun mostrarDialogoNuevaNota() {
        val etTitulo = EditText(requireContext()).apply { hint = "Nombre de la planta" }
        val etNota   = EditText(requireContext()).apply {
            hint = "Tu nota personal..."
            minLines = 3
        }
        val layout = android.widget.LinearLayout(requireContext()).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(48, 16, 48, 0)
            addView(etTitulo)
            addView(etNota)
        }
        AlertDialog.Builder(requireContext())
            .setTitle("Nueva nota herbolaria")
            .setView(layout)
            .setPositiveButton("Guardar") { _, _ ->
                val titulo = etTitulo.text.toString().trim()
                val nota   = etNota.text.toString().trim()
                if (titulo.isNotEmpty() && nota.isNotEmpty()) {
                    NotasManager.guardarNota(requireContext(), titulo, nota)
                    cargarNotas()
                } else {
                    Toast.makeText(requireContext(), "Completa los campos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarNotaCompleta(titulo: String, texto: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(titulo)
            .setMessage(texto)
            .setPositiveButton("Cerrar", null)
            .show()
    }

    private fun confirmarEliminar(titulo: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar nota")
            .setMessage("¿Eliminar la nota de \"$titulo\"?")
            .setPositiveButton("Eliminar") { _, _ ->
                NotasManager.eliminarNota(requireContext(), titulo)
                cargarNotas()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}