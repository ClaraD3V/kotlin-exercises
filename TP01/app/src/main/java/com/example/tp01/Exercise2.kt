package com.example.tp01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Exercise2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise2)

        val celsius = findViewById<EditText>(R.id.editCelsius)
        val button = findViewById<Button>(R.id.btnConvert)
        val result = findViewById<TextView>(R.id.txtResult)

        button.setOnClickListener {

            val temperature = celsius.text.toString().toDouble()

            val fahrenheit = (temperature * 9 / 5) + 32

            result.text = "Temperatura em Fahrenheit: $fahrenheit °F"
        }
    }
}