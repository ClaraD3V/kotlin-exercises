package br.unisanta.desafio.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.desafio.R
import br.unisanta.desafio.dao.EpisodioDAO
import br.unisanta.desafio.model.Episodio

class CadastroEpisodioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_episodio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtTitulo = findViewById<EditText>(R.id.edt_titulo_episodio)
        val edtAutor = findViewById<EditText>(R.id.edt_autor_episodio)
        val edtUrl = findViewById<EditText>(R.id.edt_url_episodio)

        val btnAddEpisodio = findViewById<Button>(R.id.btn_adicionar_episodio)
        val txvLinkLista = findViewById<TextView>(R.id.txv_link_lista_episodios)

        btnAddEpisodio.setOnClickListener {
            val titulo = edtTitulo.text.toString()
            val autor = edtAutor.text.toString()
            val url = edtUrl.text.toString()

            if (titulo == "" || autor == "" || url == "") {
                Toast.makeText(
                    this,
                    "Preencha os campos obrigatórios.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val episodio = Episodio(titulo, autor, url)
                EpisodioDAO.adicionarEpisodio(episodio)

                Toast.makeText(
                    this,
                    "Episódio Salvo com sucesso.",
                    Toast.LENGTH_SHORT
                ).show()

                edtTitulo.setText("")
                edtAutor.setText("")
                edtUrl.setText("")
            }
        }

        txvLinkLista.setOnClickListener{
            val intent = Intent(this, ListaEpisodioActivity::class.java)
            startActivity(intent)
        }
    }
}