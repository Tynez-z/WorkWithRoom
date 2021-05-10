package com.example.workwithroom
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workwithroom.dataBase.NoteEntity
import kotlinx.android.synthetic.main.recycleview_row.view.*
import java.util.*

class RecyclerViewAdapter(val listener: RowClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<NoteEntity>()

    fun setListData(data: ArrayList<NoteEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_row, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View, val listener: RowClickListener) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tv_title
        val tvDescription = view.tv_description
        val ivDeleteNote = view.iv_deleteNoteId

        fun bind(data: NoteEntity) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            ivDeleteNote.setOnClickListener {
                listener.deleteNoteOnClickListener(data)
            }
        }
    }

    interface RowClickListener {
        fun deleteNoteOnClickListener(note: NoteEntity)
        fun onItemClickListener(note: NoteEntity)
    }
}