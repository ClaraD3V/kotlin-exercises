package com.example.tp05.view

import android.widget.ImageView
import com.example.tp05.R
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder

fun ImageView.carregarFoto(foto: String?) {
    load(foto?.trim()?.ifBlank { null }) {
        crossfade(true)
        placeholder(R.drawable.ic_movie)
        error(R.drawable.ic_movie)
        fallback(R.drawable.ic_movie)
    }
}
