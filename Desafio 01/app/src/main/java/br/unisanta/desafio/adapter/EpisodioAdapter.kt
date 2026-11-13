package br.unisanta.desafio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.desafio.R
import br.unisanta.desafio.dao.EpisodioDAO
import br.unisanta.desafio.dao.EpisodioDAO.Companion
import br.unisanta.desafio.model.Episodio
import com.google.android.material.floatingactionbutton.FloatingActionButton



class EpisodioAdapter(private val episodios: List<Episodio>) :

    RecyclerView.Adapter<EpisodioAdapter.EpisodioViewHolder>() {

    class EpisodioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        val titulo: TextView = itemView.findViewById(R.id.txv_titulo_episodio)
        val autor: TextView = itemView.findViewById(R.id.txv_autor_episodio)
        val play: FloatingActionButton = itemView.findViewById(R.id.fab_play)
        val editar: FloatingActionButton = itemView.findViewById(R.id.fab_edita)
        val excluir: FloatingActionButton = itemView.findViewById(R.id.fab_deleta)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodioViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episodio, parent, false)

        return EpisodioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodioViewHolder, position: Int) {
        val episodio = episodios[position]

        holder.titulo.text = "${episodio.titulo}"
        holder.autor.text = "${episodio.autor}"
        holder.play.setOnClickListener{

            EpisodioDAO.reproduzirEpisodio(episodio)

        }
        holder.editar.setOnClickListener{}
        holder.excluir.setOnClickListener{
            EpisodioDAO.excluirEpisodio(episodio)
            notifyItemRemoved(position)

        }
    }

    override fun getItemCount(): Int {
        return episodios.size
    }
}