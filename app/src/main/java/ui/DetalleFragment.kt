package com.example.equipo0.ui

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.equipo0.R
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

            // Imagen con zoom suave
            imgDetalle.setImageResource(planta.imageRes)
            val zoomAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in_image)
            imgDetalle.startAnimation(zoomAnim)

            // Botón regresar con efecto rebote
            btnRegresar.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val down = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_press_down)
                        v.startAnimation(down)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        val up = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_press_up)
                        v.startAnimation(up)
                        if (event.action == MotionEvent.ACTION_UP) {
                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                    }
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}