package id.e.catatankita

import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.e.catatan_kita.R
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){
    private var onItemClickCallback: OnItemClickCallback?= null
    fun setOnItemClickCallback(OnItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = OnItemClickCallback
    }
    private lateinit var list: List<Note>

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(note: Note){
            with(itemView){
                tv_note.text = note.note
                itemView.setOnClickListener{ onItemClickCallback?.onItemClicked(note)}
                pickerTimeBtn.setOnClickListener {
                    val cal = Calendar.getInstance()
                    val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                        cal.set(Calendar.HOUR_OF_DAY, hour)
                        cal.set(Calendar.MINUTE, minute)
                        timeTv.text = SimpleDateFormat("HH:mm").format(cal.time)
                    }
                    TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
                }
            }
        }
    }

    fun setListNote(list: List<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note = list.get(position)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)

    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Note)
    }
}