package com.example.notesapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var list: List<Note> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_each_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = list[position]
        holder.tvTitle.text = note.title
        holder.tvDescription.text = note.description

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, EditNoteActivity::class.java)
            intent.putExtra("id", note.id)
            intent.putExtra("title", note.title)
            intent.putExtra("description", note.description)
            context.startActivity(intent)
        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
    }

    fun setNote(noteList: List<Note>) {
        list = noteList
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return list[position]
    }
}