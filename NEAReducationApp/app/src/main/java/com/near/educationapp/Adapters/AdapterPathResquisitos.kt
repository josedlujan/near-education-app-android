package com.near.educationapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.near.educationapp.R

class AdapterPathResquisitos(private val context: Context):RecyclerView.Adapter<AdapterPathResquisitos.ViewHolderAdapterPathRequisitos> (){


    private var datalist = ArrayList<String>()

    fun setListData(data: ArrayList<String>) {
        datalist = data
    }
    fun returnListData(): ArrayList<String> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPathResquisitos.ViewHolderAdapterPathRequisitos {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_path_requisitos, parent, false)
        return ViewHolderAdapterPathRequisitos(view)
    }


    override fun onBindViewHolder(holder: AdapterPathResquisitos.ViewHolderAdapterPathRequisitos, position: Int) {
        val Objeto = datalist[position]
        holder.bindView(Objeto)
    }


    inner class ViewHolderAdapterPathRequisitos(itemView: View):RecyclerView.ViewHolder(itemView){

        private lateinit var requisito:TextView
        fun bindView(Objeto: String) {
            requisito = itemView.findViewById(R.id.item_path_requisito)
            requisito.text = "• ${Objeto}"
        }
    }
}