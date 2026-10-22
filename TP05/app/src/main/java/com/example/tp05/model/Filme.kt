package com.example.tp05.model

data class Filme(
    val id: Long,
    val nome: String,
    val nota: Float,
    val foto: String = "",
)
