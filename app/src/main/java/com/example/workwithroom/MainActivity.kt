package com.example.workwithroom
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.workwithroom.dataBase.NoteEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllNotesObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        btn_save.setOnClickListener {
            val title = et_titleNote.text.toString()
            val description = et_descriptionNote.text.toString()

            if (btn_save.text.equals("Save")) {
                val note = NoteEntity(0, title, description)
                viewModel.insertNoteInfo(note)
            } else {
                val note = NoteEntity(
                    et_titleNote.getTag(et_titleNote.id).toString().toInt(),
                    title,
                    description
                )
                viewModel.updateNoteInfo(note)
                btn_save.setText("Save")
            }
            et_titleNote.setText("")
            et_descriptionNote.setText("")
        }
    }

    override fun deleteNoteOnClickListener(note: NoteEntity) {
        viewModel.deleteeNoteInfo(note)
    }

    //update
    override fun onItemClickListener(note: NoteEntity) {
        et_titleNote.setText(note.title)
        et_descriptionNote.setText(note.description)
        et_titleNote.setTag(et_titleNote.id, note.id)
        btn_save.setText("Update")
    }
}