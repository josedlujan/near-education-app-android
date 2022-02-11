package com.near.educationapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.near.educationapp.LeccionesActivity
import com.near.educationapp.LevesActivity
import com.near.educationapp.Models.ModelLecciones
import com.near.educationapp.Models.ModelLevels
import com.near.educationapp.PreguntasTestActivity
import com.near.educationapp.R

class AdapterLecciones (private val context: Context):RecyclerView.Adapter<AdapterLecciones.ViewHolderAdapterLecciones>() {

    private var datalist = mutableListOf<ModelLecciones>()

    fun setListData(data: MutableList<ModelLecciones>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelLecciones> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapterLecciones {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_lecciones, parent, false)
        return ViewHolderAdapterLecciones(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapterLecciones, position: Int) {
        val Objeto = datalist[position]
        holder.bindView(Objeto)
    }

    inner class ViewHolderAdapterLecciones(itemView: View):RecyclerView.ViewHolder(itemView){

        lateinit var title: TextView
        lateinit var card: LinearLayout

        fun bindView(Objeto:ModelLecciones){
            title = itemView.findViewById(R.id.item_lecciones_title)
            card = itemView.findViewById(R.id.item_lecciones_cotenedor)

            title.text = Objeto.titulo

            card.setOnClickListener {
                context.startActivity(
                        Intent(context, LeccionesActivity::class.java)
                            .putExtra("titulo","${Objeto.titulo}")
                            .putExtra("autor","${Objeto.autor}")
                            .putExtra("articulo","${Objeto.articulo}")
                            .putExtra("fecha","${Objeto.fecha}")
                            .putExtra("foto","${Objeto.foto}"))
            }
        }
    }

}