package br.unisanta.desafio.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.desafio.R
import br.unisanta.desafio.adapter.EpisodioAdapter
import br.unisanta.desafio.dao.EpisodioDAO
import br.unisanta.desafio.model.Episodio
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaEpisodioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_episodio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerViewEpisodios = findViewById<RecyclerView>(R.id.recyclerView_episodios)
        val episodios = EpisodioDAO.obterEpisodios()
        recyclerViewEpisodios.layoutManager = LinearLayoutManager(this)
        recyclerViewEpisodios.adapter = EpisodioAdapter(episodios)
    }
}