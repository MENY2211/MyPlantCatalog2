package com.example.equipo0.util

import android.content.Context

object FavoritosManager {

    private const val PREFS_NAME = "favoritos_prefs"
    private const val KEY_FAVORITOS = "favoritos_ids"

    fun obtenerFavoritos(context: Context): Set<Int> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val stringSet = prefs.getStringSet(KEY_FAVORITOS, emptySet()) ?: emptySet()
        return stringSet.map { it.toInt() }.toSet()
    }

    fun esFavorito(context: Context, plantaId: Int): Boolean {
        return plantaId in obtenerFavoritos(context)
    }

    fun toggleFavorito(context: Context, plantaId: Int): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val actuales = obtenerFavoritos(context).toMutableSet()
        val ahora: Boolean
        if (plantaId in actuales) {
            actuales.remove(plantaId)
            ahora = false
        } else {
            actuales.add(plantaId)
            ahora = true
        }
        prefs.edit()
            .putStringSet(KEY_FAVORITOS, actuales.map { it.toString() }.toSet())
            .apply()
        return ahora
    }
}