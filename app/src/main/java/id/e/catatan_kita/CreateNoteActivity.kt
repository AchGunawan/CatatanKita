package id.e.catatan_kita

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_create_note.*

class CreateNoteActivity : AppCompatActivity() {
    companion object {
        val NEW_NOTE = "new note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        btn_save_note.setOnClickListener {
            val resultIntent = Intent()
            if(!TextUtils.isEmpty(et_note.text)){
                val note = et_note.text.toString()
                resultIntent.putExtra(NEW_NOTE,note)
                setResult(Activity.RESULT_OK, resultIntent)
            }else{
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}