package com.example.tp02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val title = findViewById<EditText>(R.id.editTitle)
        val author = findViewById<EditText>(R.id.editAuthor)
        val button = findViewById<Button>(R.id.btnRegister)

        button.setOnClickListener {

            val bookTitle = title.text.toString()
            val bookAuthor = author.text.toString()

            if (bookTitle == "" || bookAuthor == "") {

                Toast.makeText(
                    this,
                    "Preencha todos os campos.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent = Intent(this, BookDetailsActivity::class.java)

                intent.putExtra("title", bookTitle)
                intent.putExtra("author", bookAuthor)

                startActivity(intent)
            }
        }
    }
}