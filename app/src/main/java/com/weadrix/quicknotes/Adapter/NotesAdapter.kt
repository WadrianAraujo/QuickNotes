package com.weadrix.quicknotes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.weadrix.quicknotes.Models.Note
import com.weadrix.quicknotes.R

class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private val notesList : ArrayList<Note>()
    private val fullList : ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true
    }

    fun RandomColor() : Int {

        val list = ArrayList<Int>()
        list.add(R.color.noteColor_AmareloPastel)
        list.add(R.color.noteColor_AzulPastel)
        list.add(R.color.noteColor_CremePastel)
        list.add(R.color.noteColor_LaranjaPastel)
        list.add(R.color.noteColor_LilasPastel)
        list.add(R.color.noteColor_AzulCelestePastel)
        list.add(R.color.noteColor_MentaPastel)
        list.add(R.color.noteColor_PessegoPastel)
        list.add(R.color.noteColor_RosaPastel)
        list.add(R.color.noteColor_VerdePastel)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }
}