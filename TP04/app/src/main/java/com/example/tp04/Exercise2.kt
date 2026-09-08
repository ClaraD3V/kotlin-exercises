package com.example.tp04

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Exercise2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise2)

        val name = findViewById<EditText>(R.id.editTaskName)
        val description = findViewById<EditText>(R.id.editTaskDescription)

        val addButton = findViewById<Button>(R.id.btnAddTask)

        val recyclerTasks = findViewById<RecyclerView>(R.id.recyclerTasks)

        val tasks = ArrayList<Task>()

        val adapter = TaskAdapter(tasks)

        recyclerTasks.adapter = adapter

        recyclerTasks.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {

            val taskName = name.text.toString()
            val taskDescription = description.text.toString()

            if (taskName == "" || taskDescription == "") {

                Toast.makeText(
                    this,
                    "Preencha o nome e a descrição.",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val task = Task(
                    taskName,
                    taskDescription,
                    false
                )

                tasks.add(task)

                adapter.notifyItemInserted(tasks.size - 1)

                name.setText("")
                description.setText("")
            }
        }
    }
}