package com.example.tp04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnExercise1 = findViewById<Button>(R.id.btnExercise1)
        val btnExercise2 = findViewById<Button>(R.id.btnExercise2)

        btnExercise1.setOnClickListener {
            val intent = Intent(this, Exercise1::class.java)
            startActivity(intent)
        }

        btnExercise2.setOnClickListener {
            val intent = Intent(this, Exercise2::class.java)
            startActivity(intent)
        }
    }
}