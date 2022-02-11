package com.near.educationapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.LevesActivity
import com.near.educationapp.Models.ModelLevels
import com.near.educationapp.PreguntasTestActivity
import com.near.educationapp.R
import com.near.educationapp.RecuperarActivity

class AdapterLevels (private val context: Context,val correo:String): RecyclerView.Adapter<AdapterLevels.ViewHolderAdapterLevels>() {

    private val bd = FirebaseFirestore.getInstance()
    private var datalist = mutableListOf<ModelLevels>()

    fun setListData(data: MutableList<ModelLevels>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelLevels> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdapterLevels.ViewHolderAdapterLevels {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_test_levels, parent, false)
        return ViewHolderAdapterLevels(view)
    }


    override fun onBindViewHolder(holder: AdapterLevels.ViewHolderAdapterLevels, position: Int) {
        val Level = datalist[position]
        holder.bindView(Level)
    }
    inner class ViewHolderAdapterLevels(itemView: View):RecyclerView.ViewHolder(itemView){

        lateinit var title: TextView
        lateinit var resumen: TextView
        lateinit var card: LinearLayout

        lateinit var cero:TextView
        lateinit var contenedor_res: LinearLayout
        lateinit var res_e:TextView
        lateinit var res_c:TextView

        fun bindView(Level: ModelLevels) {
            title = itemView.findViewById(R.id.item_level_title)
            resumen = itemView.findViewById(R.id.item_level_resumen)
            card = itemView.findViewById(R.id.item_level_cotenedor)

            contenedor_res = itemView.findViewById(R.id.item_test_puntuacion_grupo)
            cero = itemView.findViewById(R.id.item_test_puntuacion_cero)
            res_c = itemView.findViewById(R.id.item_test_puntuacion_correctos)
            res_e = itemView.findViewById(R.id.item_test_puntuacion_errores)

            title.text = "Nivel: ${Level.nivel_number}"
            resumen.text = "Nivel: ${Level.nivel_text}"


            bd.collection("TestCalificaciones")
                .whereEqualTo("id_nivel",Level.id)
                .whereEqualTo("id_categoria",Level.id_categoria)
                .whereEqualTo("email",correo)
                .get().addOnSuccessListener {
                    var c:Int = -1
                    var e:Int = -1
                    for (documentos in it) {
                        c = documentos.get("correctos").toString().toInt()
                        e = documentos.get("errores").toString().toInt()
                        break
                    }
                    if (e != -1 && c != -1){
                        cero.visibility = View.GONE
                        contenedor_res.visibility = View.VISIBLE
                        res_c.text = "${c}"
                        res_e.text = "${e}"
                    }else{
                        cero.visibility = View.VISIBLE
                        contenedor_res.visibility = View.GONE
                    }
                }


            card.setOnClickListener {
                if (context is LevesActivity){
                    context.startActivity(Intent(context,PreguntasTestActivity::class.java)
                        .putExtra("id_categoria","${Level.id_categoria}")
                        .putExtra("id_nivel","${Level.id}")
                        .putExtra("nivel","${Level.nivel_number}"))
                }
            }
        }

    }
}


