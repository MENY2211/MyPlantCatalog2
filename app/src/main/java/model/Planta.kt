package com.example.equipo0.model

import com.example.equipo0.R

data class Planta(
    val id: Int,
    val nombreComun: String,
    val nombreCientifico: String,
    val descripcion: String,
    val usos: String,
    val preparacion: String,
    val contraindicaciones: String,
    val imageRes: Int
)

object PlantasData {
    val lista = listOf(
        Planta(
            id = 1,
            nombreComun = "Hierba Santa",
            nombreCientifico = "Piper auritum Kunth",
            descripcion = "Planta aromática de hojas grandes, muy usada en la medicina tradicional de Tabasco para problemas digestivos.",
            usos = "Digestivo, cólicos, náuseas, inflamación estomacal",
            preparacion = "Hervir 2-3 hojas en 1 taza de agua por 5 minutos. Tomar tibio después de comer.",
            contraindicaciones = "No usar durante el embarazo. Evitar en niños menores de 2 años.",
            imageRes = R.drawable.planta_hierba_santa
        ),
        Planta(
            id = 2,
            nombreComun = "Chaya",
            nombreCientifico = "Cnidoscolus aconitifolius",
            descripcion = "Arbusto nativo de la región maya, conocido como el árbol espinaca. Rico en hierro, calcio y vitaminas.",
            usos = "Diabetes, colesterol alto, anemia, fortalece huesos",
            preparacion = "Cocinar mínimo 10 minutos antes de consumir. Nunca comer cruda. Usar 3-5 hojas en agua o caldo.",
            contraindicaciones = "⚠️ NUNCA consumir cruda — contiene glucósidos tóxicos que se eliminan con la cocción.",
            imageRes = R.drawable.planta_chaya
        ),
        Planta(
            id = 3,
            nombreComun = "Albahaca Morada",
            nombreCientifico = "Ocimum basilicum var. purpurascens",
            descripcion = "Variedad morada de la albahaca, con propiedades relajantes y ansiolíticas usadas en Tabasco.",
            usos = "Nervios, estrés, insomnio, dolor de cabeza tensional",
            preparacion = "Infusión de 5-6 hojas en agua caliente por 10 minutos. Tomar antes de dormir.",
            contraindicaciones = "No combinar con medicamentos sedantes. Consultar médico si se toman antidepresivos.",
            imageRes = R.drawable.planta_albahaca_morada
        ),
        Planta(
            id = 4,
            nombreComun = "Maguey Morado",
            nombreCientifico = "Tradescantia spathacea",
            descripcion = "Planta de hojas bicolores (verde y morado) ampliamente usada en Tabasco como antiinflamatorio natural.",
            usos = "Inflamaciones, heridas leves, tos, asma, antiséptico",
            preparacion = "Hervir 4-5 hojas en 2 tazas de agua por 10 minutos. Tomar 2 veces al día o aplicar en heridas.",
            contraindicaciones = "No usar en embarazo. Puede causar irritación en piel sensible.",
            imageRes = R.drawable.planta_maguey_morado
        )
    )
}