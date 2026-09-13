package br.unisanta.appimc.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.appimc.R
import br.unisanta.appimc.dao.IMCdao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IMCActivity : AppCompatActivity(R.layout.activity_imc) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txvResultado = findViewById<TextView>(R.id.txv_resultado)
        val txvClassificacao = findViewById<TextView>(R.id.txv_classificacao)
        val fabVolta = findViewById<FloatingActionButton>(R.id.fab_volta)
        val dao = IMCdao()
        val imc = dao.buscar()

        txvResultado.setText("Resultado: ${imc?.imc}")
        txvClassificacao.setText("Resultado: ${imc?.classificacao}")

        fabVolta.setOnClickListener {
            finish()
        }
    }
}