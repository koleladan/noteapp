package com.example.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.databinding.NotelListBinding

class recyclerviewAdapter(val context: Context, val noteClickDeleteInterface: MainActivity, val noteClickInterface: MainActivity)
    :RecyclerView.Adapter<recyclerviewAdapter.viewHolder>(){
    val allNotes = ArrayList<Note>()

    inner class viewHolder( val binding:NotelListBinding): RecyclerView.ViewHolder(binding.root){
        val noteTv = binding.title
        val timeTv =binding.timestamp
        val deleteTv = binding.delete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotelListBinding.inflate(inflater, parent, false)
        return viewHolder(binding)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.noteTv.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("Last Update"+allNotes.get(position).timestamp)
        holder.deleteTv.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener(){
            noteClickInterface.onNoteClick(allNotes.get(position))
        }


    }

    override fun getItemCount(): Int {
      return allNotes.size
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note: Note)
}
interface NoteClickInterface{
    fun onNoteClick(note: Note)
}