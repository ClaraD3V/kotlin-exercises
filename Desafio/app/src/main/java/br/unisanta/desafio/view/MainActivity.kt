package br.unisanta.desafio.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.desafio.R
import br.unisanta.desafio.dao.LoginDAO


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtUsuario =  findViewById<EditText>(R.id.edt_usuario)
        val edtSenha =  findViewById<EditText>(R.id.edt_senha)


        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val usuario = edtUsuario.text.toString()
            val senha = edtSenha.text.toString()
            val dao = LoginDAO()
            val resultado = dao.validar(usuario,senha)
            if (resultado){
                 val intent = Intent(this, CadastroEpisodioActivity::class.java)
                 startActivity(intent)
            }else {
                Toast.makeText(
                    this,
                    "Usuário ou senha inválidos.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }


    }
}