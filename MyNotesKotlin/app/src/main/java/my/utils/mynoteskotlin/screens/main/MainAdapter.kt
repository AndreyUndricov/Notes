package my.utils.mynoteskotlin.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import my.utils.mynoteskotlin.R
import my.utils.mynoteskotlin.models.AppNote

class MainAdapter:RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var listNotes= emptyList<AppNote>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int=listNotes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameNote.text=listNotes[position].name
        holder.textNote.text=listNotes[position].text
    }


    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.click(listNotes[holder.adapterPosition])

        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val nameNote:TextView=view.item_note_name
        val textNote:TextView=view.item_note_text
    }
    fun setList(list:List<AppNote>){
        listNotes=list
        notifyDataSetChanged()
    }
}