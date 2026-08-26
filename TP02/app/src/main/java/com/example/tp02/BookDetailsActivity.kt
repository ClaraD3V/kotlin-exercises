package com.example.tp02

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_details)

        val title = findViewById<TextView>(R.id.txtTitle)
        val author = findViewById<TextView>(R.id.txtAuthor)
        val buttonBack = findViewById<FloatingActionButton>(R.id.btnBack)

        val bookTitle = intent.getStringExtra("title")
        val bookAuthor = intent.getStringExtra("author")

        title.text = "Título: $bookTitle"
        author.text = "Autor: $bookAuthor"

        buttonBack.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}