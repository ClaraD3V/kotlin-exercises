package br.unisanta.appalunodao.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.appalunodao.R
import br.unisanta.appalunodao.dao.AlunoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import kotlin.random.Random

class AlunoActivity : AppCompatActivity(R.layout.activity_aluno) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txvNomeAluno = findViewById<TextView>(R.id.txv_nome_aluno)
        val txvTurmaAluno = findViewById<TextView>(R.id.txv_turma_aluno)
        val fabVoltaTelaCadastro = findViewById<FloatingActionButton>(R.id.fab_volta_tela_cadastro)
        val btnGerar = findViewById<Button>(R.id.btn_gerar)
        val txvRA = findViewById<TextView>(R.id.txv_RA_aluno)
        val dao = AlunoDAO()
        val aluno = dao.buscar()
        txvNomeAluno.setText("Nome: ${aluno?.nome}")
        txvTurmaAluno.setText("Turma: ${aluno?.turma}")

        fabVoltaTelaCadastro.setOnClickListener {
            finish()
        }

        btnGerar.setOnClickListener {
            val ra = Random.nextInt(100000, 1000000 )
            txvRA.setText("RA: $ra")

        }

    }
}