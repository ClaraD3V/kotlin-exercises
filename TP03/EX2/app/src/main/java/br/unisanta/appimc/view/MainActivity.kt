package br.unisanta.appimc.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.appimc.R
import br.unisanta.appimc.dao.IMCdao
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
        val edtPeso = findViewById<EditText>(R.id.edt_peso)
        val edtAltura = findViewById<EditText>(R.id.edt_altura)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val fabAvanca = findViewById<FloatingActionButton>(R.id.fab_avanca)
        val dao = IMCdao()

        btnCalcular.setOnClickListener {
            val peso =edtPeso.text.toString().toDouble()
            val altura =edtAltura.text.toString().toDouble()
            val imcResultado = peso / (altura * altura)
            val classificacao = when {
                imcResultado < 18.5 -> "Abaixo do peso"
                imcResultado < 25.0 -> "Peso normal"
                imcResultado < 30.0 -> "Sobrepeso"
                else -> "Obesidade"
            }
            dao.salvar(imcResultado, classificacao)
            Log.i("ESTADO","SALVO")
        }

        fabAvanca.setOnClickListener{
            val intent = Intent(this,IMCActivity::class.java)
            startActivity(intent)
        }

    }
}