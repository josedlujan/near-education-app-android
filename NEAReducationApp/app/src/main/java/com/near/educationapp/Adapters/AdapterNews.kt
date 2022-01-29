package com.near.educationapp.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.near.educationapp.Models.ModelNews
import com.near.educationapp.R

class AdapterNews(private val context: Context): RecyclerView.Adapter<AdapterNews.ViewHolderAdapterNews>() {

    private var datalist = mutableListOf<ModelNews>()

    fun setListData(data: MutableList<ModelNews>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelNews> {
        return datalist;
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdapterNews.ViewHolderAdapterNews {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_inicio, parent, false)
        return ViewHolderAdapterNews(view)
    }

    override fun onBindViewHolder(holder: AdapterNews.ViewHolderAdapterNews, position: Int) {
        val news = datalist[position]
        holder.bindView(news)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    inner class ViewHolderAdapterNews(itemView: View):RecyclerView.ViewHolder(itemView){

        lateinit var date:TextView
        lateinit var title:TextView
        lateinit var resumen:TextView
        lateinit var photo: ImageView
        lateinit var card: CardView

        fun bindView(news: ModelNews){
            date = itemView.findViewById(R.id.item_news_date)
            title = itemView.findViewById(R.id.item_news_title)
            resumen = itemView.findViewById(R.id.item_news_resumen)
            photo = itemView.findViewById(R.id.item_news_photo)
            card = itemView.findViewById(R.id.item_news_card)

            date.text = news.date
            title.text = news.title
            resumen.text = news.resumen
            Glide.with(context).load(datalist[position].photo).into(photo)

            card.setOnClickListener {
                try {
                    var uri:Uri = Uri.parse(datalist[position].link)
                    context.startActivity(Intent(Intent.ACTION_VIEW,uri))
                }catch (e:Exception){
                    Toast.makeText(context, "link dañanado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}