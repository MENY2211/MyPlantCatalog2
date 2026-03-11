package com.example.equipo0.util

import android.content.Context

object NotasManager {

    private const val PREFS_NAME = "mis_plantas_prefs"

    fun obtenerNotas(context: Context): Map<String, String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.all
            .filter { it.value is String }
            .mapValues { it.value as String }
    }

    fun guardarNota(context: Context, titulo: String, nota: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(titulo, nota)
            .apply()
    }

    fun eliminarNota(context: Context, titulo: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(titulo)
            .apply()
    }
}