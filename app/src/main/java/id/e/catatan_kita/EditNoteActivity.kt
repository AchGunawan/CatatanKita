package id.e.catatan_kita

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.e.catatankita.EditNoteViewModel
import id.e.catatankita.Note
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    private lateinit var model: EditNoteViewModel
    private lateinit var note: LiveData<Note>

    companion object{
        const val NOTE_ID = "note_id"
        const val UPDATE_NOTE = "update_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val id = intent.getStringExtra(NOTE_ID)
        model = ViewModelProvider(this).get(EditNoteViewModel::class.java)
        note = id?.let { model.getNote(it) }!!
        note.observe(this, object: Observer<Note> {
            override fun onChanged(t: Note?) {
                etEditNote.setText(t?.note)
            }
        })
        btnUpdate.setOnClickListener {
            val updateNote = etEditNote.text.toString()
            Intent().also {
                it.putExtra(NOTE_ID, id)
                it.putExtra(UPDATE_NOTE, updateNote)
                setResult(Activity.RESULT_OK, it)
                finish()
            }
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }
}