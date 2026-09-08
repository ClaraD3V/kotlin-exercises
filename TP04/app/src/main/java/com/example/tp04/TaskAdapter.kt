package com.example.tp04

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.txtTaskName)
        val description: TextView = itemView.findViewById(R.id.txtTaskDescription)
        val completeButton: Button = itemView.findViewById(R.id.btnComplete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = tasks[position]

        holder.name.text = task.name
        holder.description.text = task.description

        if (task.completed) {

            holder.completeButton.text = "Desmarcar"
            holder.name.paintFlags =
                holder.name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        } else {

            holder.completeButton.text = "Concluir"
            holder.name.paintFlags =
                holder.name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.completeButton.setOnClickListener {

            task.completed = !task.completed

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}