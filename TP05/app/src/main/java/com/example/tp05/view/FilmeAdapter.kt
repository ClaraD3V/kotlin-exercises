package com.example.tp05.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp05.R
import com.example.tp05.databinding.ItemFilmeBinding
import com.example.tp05.model.Filme

class FilmeAdapter(
    private val aoEditar: (Filme) -> Unit,
    private val aoExcluir: (Filme) -> Unit,
) : ListAdapter<Filme, FilmeAdapter.FilmeViewHolder>(Diferenca) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val binding = ItemFilmeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FilmeViewHolder(private val binding: ItemFilmeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(filme: Filme) {
            binding.txtNome.text = filme.nome
            binding.ratingNota.rating = filme.nota
            binding.txtNota.text = binding.root.context.getString(R.string.nota_formato, filme.nota)

            binding.imgFoto.carregarFoto(filme.foto)

            binding.btnEditar.setOnClickListener { aoEditar(filme) }
            binding.btnExcluir.setOnClickListener { aoExcluir(filme) }
        }
    }

    private object Diferenca : DiffUtil.ItemCallback<Filme>() {
        override fun areItemsTheSame(old: Filme, new: Filme) = old.id == new.id
        override fun areContentsTheSame(old: Filme, new: Filme) = old == new
    }
}
