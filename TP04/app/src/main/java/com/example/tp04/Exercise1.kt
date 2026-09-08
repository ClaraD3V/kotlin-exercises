package com.example.tp04

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Exercise1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise1)

        val title = findViewById<EditText>(R.id.editTitle)
        val director = findViewById<EditText>(R.id.editDirector)

        val addButton = findViewById<Button>(R.id.btnAddMovie)
        val changeLayoutButton = findViewById<Button>(R.id.btnChangeLayout)

        val recyclerMovies = findViewById<RecyclerView>(R.id.recyclerMovies)

        val movies = ArrayList<Movie>()

        val adapter = MovieAdapter(movies)

        recyclerMovies.adapter = adapter

        recyclerMovies.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {

            val movieTitle = title.text.toString()
            val movieDirector = director.text.toString()

            if (movieTitle == "" || movieDirector == "") {

                Toast.makeText(
                    this,
                    "Preencha o título e o diretor.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val movie = Movie(movieTitle, movieDirector)

                movies.add(movie)

                adapter.notifyItemInserted(movies.size - 1)

                title.setText("")
                director.setText("")
            }
        }

        changeLayoutButton.setOnClickListener {

            if (recyclerMovies.layoutManager is GridLayoutManager) {

                recyclerMovies.layoutManager = LinearLayoutManager(this)

            } else {

                recyclerMovies.layoutManager = GridLayoutManager(this, 2)
            }
        }
    }
}