package com.example.equipo0.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.equipo0.R
import com.example.equipo0.databinding.FragmentHomeBinding
import com.example.equipo0.util.setBounceTouchEffect

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Efecto rebote en el botón principal
        binding.btnVerCatalogo.setBounceTouchEffect()

        binding.btnVerCatalogo.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_catalogo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}