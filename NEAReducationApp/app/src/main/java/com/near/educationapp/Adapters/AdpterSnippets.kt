package com.near.educationapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.near.educationapp.LevesActivity
import com.near.educationapp.Models.ModelSnippets
import com.near.educationapp.R
import com.near.educationapp.SnippetsListActivity

class AdpterSnippets(private val context: Context):RecyclerView.Adapter<AdpterSnippets.ViewHolderAdapterSnippets>() {

    private var datalist = mutableListOf<ModelSnippets>()

    fun setListData(data: MutableList<ModelSnippets>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelSnippets> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdpterSnippets.ViewHolderAdapterSnippets {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_snippets, parent, false)
        return ViewHolderAdapterSnippets(view)
    }

    override fun onBindViewHolder(holder: AdpterSnippets.ViewHolderAdapterSnippets, position: Int) {
        val Snippets = datalist[position]
        holder.bindView(Snippets)
    }

    inner class ViewHolderAdapterSnippets(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var title: TextView
        lateinit var photo: ImageView
        lateinit var card: CardView

        fun bindView(Snippets:ModelSnippets){
            title = itemView.findViewById(R.id.item_snippets_title)
            photo = itemView.findViewById(R.id.item_snippets_photo)
            card = itemView.findViewById(R.id.item_snippets_card)

            title.text = Snippets.name
            Glide.with(context).load(Snippets.photo).into(photo)




            card.setOnClickListener {
                context.startActivity(
                    Intent(context, SnippetsListActivity::class.java)
                    .putExtra("name",Snippets.name)
                    .putExtra("id",Snippets.id))
            }
        }
    }


}