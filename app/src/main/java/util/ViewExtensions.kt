package com.example.equipo0.util

import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import com.example.equipo0.R

/**
 * Aplica efecto de escala/rebote al presionar cualquier View.
 * Uso: miBoton.setBounceTouchEffect()
 */
fun View.setBounceTouchEffect() {
    setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val down = AnimationUtils.loadAnimation(context, R.anim.btn_press_down)
                v.startAnimation(down)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val up = AnimationUtils.loadAnimation(context, R.anim.btn_press_up)
                v.startAnimation(up)
            }
        }
        false // false para que el click normal también funcione
    }
}