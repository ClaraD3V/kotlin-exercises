package br.unisanta.appalunodao.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.appalunodao.R
import br.unisanta.appalunodao.dao.AlunoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtNomeAluno = findViewById<EditText>(R.id.edt_nome_aluno)
        val edtTurmaAluno = findViewById<EditText>(R.id.edt_turma_aluno)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        val fabAvancaTelaAluno = findViewById<FloatingActionButton>(R.id.fab_avanca)
        val dao =  AlunoDAO()
        btnSalvar.setOnClickListener{
            val nomeAluno = edtNomeAluno.text.toString()
            val turmaAluno = edtTurmaAluno.text.toString()
            dao.salvar(nomeAluno, turmaAluno)
            Log.i("ESTADO","SALVO")
        }

        fabAvancaTelaAluno.setOnClickListener{
            val intent = Intent(this,AlunoActivity::class.java)
            startActivity(intent)
        }

    }
}