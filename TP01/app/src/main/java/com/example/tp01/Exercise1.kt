package com.example.tp01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Exercise1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise1)

        val name = findViewById<EditText>(R.id.editName)
        val age = findViewById<EditText>(R.id.editAge)
        val button = findViewById<Button>(R.id.btnWelcome)
        val result = findViewById<TextView>(R.id.txtResult)

        button.setOnClickListener {

            val userName = name.text.toString()
            val userAge = age.text.toString()

            result.text = "Olá, $userName! Você tem $userAge anos."
        }
    }
}