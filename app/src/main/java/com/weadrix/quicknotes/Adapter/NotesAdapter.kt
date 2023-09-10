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
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener : NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))

        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(notesList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateList(newList : List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)

        notifyDataSetChanged()
    }

    fun filterList(search:String){
        notesList.clear()

        for (item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true){
                notesList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    fun randomColor() : Int {

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

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }

    interface NotesClickListener{

        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }
}