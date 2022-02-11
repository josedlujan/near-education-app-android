package com.near.educationapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.near.educationapp.Models.ModelSnippetsList
import com.near.educationapp.R
import com.near.educationapp.SnippetsCodeActivity
import com.near.educationapp.SnippetsListActivity

class AdapterSnippetsList(private val context: Context): RecyclerView.Adapter<AdapterSnippetsList.ViewHolderAdapterSnippetsList>() {

    private var datalist = mutableListOf<ModelSnippetsList>()

    fun setListData(data: MutableList<ModelSnippetsList>) {
        datalist = data
    }

    fun returnListData(): MutableList<ModelSnippetsList> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdapterSnippetsList.ViewHolderAdapterSnippetsList {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_snippets_list, parent, false)
        return ViewHolderAdapterSnippetsList(view)
    }

    override fun onBindViewHolder(holder: AdapterSnippetsList.ViewHolderAdapterSnippetsList, position: Int) {
        val SnippetsList = datalist[position]
        holder.bindView(SnippetsList)
    }

    inner class ViewHolderAdapterSnippetsList(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var title: TextView
        lateinit var card: LinearLayout

        fun bindView(SnippetsList: ModelSnippetsList) {
            title = itemView.findViewById(R.id.item_snippets_list_title)
            card = itemView.findViewById(R.id.item_snippets_list_cotenedor)

            title.text = SnippetsList.titulo

            card.setOnClickListener {
                context.startActivity(
                    Intent(context, SnippetsCodeActivity::class.java)
                        .putExtra("titulo", SnippetsList.titulo)
                        .putExtra("codigo", SnippetsList.codigo)
                )
            }
        }
    }
}




