package com.near.educationapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.near.educationapp.Models.ModelPath
import com.near.educationapp.PathActivity
import com.near.educationapp.R

class AdapterPath(private val context: Context):RecyclerView.Adapter<AdapterPath.ViewHolderAdapterPath>() {

    private var datalist = mutableListOf<ModelPath>()

    fun setListData(data: MutableList<ModelPath>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelPath> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPath.ViewHolderAdapterPath {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_path, parent, false)
        return ViewHolderAdapterPath(view)
    }


    override fun onBindViewHolder(holder: AdapterPath.ViewHolderAdapterPath, position: Int) {
        val Objeto = datalist[position]
        holder.bindView(Objeto)
    }

    inner class ViewHolderAdapterPath(itemView: View):RecyclerView.ViewHolder(itemView){

        private lateinit var card:LinearLayout
        private lateinit var nombre:TextView
        private lateinit var activo:TextView
        private lateinit var icon:ImageView

        fun bindView(Objeto: ModelPath) {
            card = itemView.findViewById(R.id.item_path_list_cotenedor)
            nombre = itemView.findViewById(R.id.item_path_title)
            activo = itemView.findViewById(R.id.item_path_activo)
            icon = itemView.findViewById(R.id.item_path_icon)

            nombre.text = Objeto.nombre

            if (Objeto.activo){
                nombre.setTextColor(ContextCompat.getColor(context,R.color.color_pregunta))
                activo.visibility = View.GONE
                icon.setImageResource(R.drawable.icon_arrow)
                card.setOnClickListener {

                context.startActivity(Intent(context, PathActivity::class.java)
                    .putExtra("id_categoria","${Objeto.id}")
                    .putExtra("nombre","${Objeto.nombre}"))

                }
            }else{
                nombre.setTextColor(ContextCompat.getColor(context, R.color.color_texto_default))
                icon.setImageResource(R.drawable.icon_arrow_2)
            }
        }

    }
}