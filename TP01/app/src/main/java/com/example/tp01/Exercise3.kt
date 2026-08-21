package com.example.tp01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Exercise3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise3)

        val voltage = findViewById<EditText>(R.id.editVoltage)
        val resistance = findViewById<EditText>(R.id.editResistance)
        val current = findViewById<EditText>(R.id.editCurrent)

        val button = findViewById<Button>(R.id.btnCalculate)
        val result = findViewById<TextView>(R.id.txtResult)

        button.setOnClickListener {

            val v = voltage.text.toString()
            val r = resistance.text.toString()
            val i = current.text.toString()

            if (v == "" && r != "" && i != "") {

                val resistanceValue = r.toDouble()
                val currentValue = i.toDouble()

                val voltageResult = resistanceValue * currentValue

                result.text = "Tensão = $voltageResult V"

            } else if (v != "" && r == "" && i != "") {

                val voltageValue = v.toDouble()
                val currentValue = i.toDouble()

                val resistanceResult = voltageValue / currentValue

                result.text = "Resistência = $resistanceResult Ω"

            } else if (v != "" && r != "" && i == "") {

                val voltageValue = v.toDouble()
                val resistanceValue = r.toDouble()

                val currentResult = voltageValue / resistanceValue

                result.text = "Corrente = $currentResult A"

            } else {

                result.text = "Preencha exatamente dois campos."

            }
        }
    }
}