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
import com.near.educationapp.InicioActivity
import com.near.educationapp.LevesActivity
import com.near.educationapp.Models.ModelTestTitle
import com.near.educationapp.R

class AdapterTestTitle(private val context: Context): RecyclerView.Adapter<AdapterTestTitle.ViewHolderAdapterTestTitle>() {

    private var datalist = mutableListOf<ModelTestTitle>()

    fun setListData(data: MutableList<ModelTestTitle>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelTestTitle> {
        return datalist;
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdapterTestTitle.ViewHolderAdapterTestTitle {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_test, parent, false)
        return ViewHolderAdapterTestTitle(view)
    }

    override fun onBindViewHolder(holder: AdapterTestTitle.ViewHolderAdapterTestTitle,position: Int) {
        val Title = datalist[position]
        holder.bindView(Title)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class ViewHolderAdapterTestTitle(itemView: View):RecyclerView.ViewHolder(itemView){

        lateinit var title: TextView
        lateinit var photo: ImageView
        lateinit var card: CardView

        fun bindView(Title: ModelTestTitle){
            title = itemView.findViewById(R.id.item_test_title)
            photo = itemView.findViewById(R.id.item_test_photo)
            card = itemView.findViewById(R.id.item_test_card)

            title.text = Title.name
            Glide.with(context).load(Title.photo).into(photo)

            card.setOnClickListener {
                context.startActivity(Intent(context,LevesActivity::class.java)
                    .putExtra("name",Title.name)
                    .putExtra("id",Title.id))
            }
        }
    }
}