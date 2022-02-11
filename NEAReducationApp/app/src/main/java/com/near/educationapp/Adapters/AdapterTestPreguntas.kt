package com.near.educationapp.Adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.near.educationapp.Models.ModelTestPreguntas
import com.near.educationapp.PreguntasTestActivity
import com.near.educationapp.R

class AdapterTestPreguntas (private val context: Context): RecyclerView.Adapter<AdapterTestPreguntas.ViewHolderAdapterTestPreguntas>() {

    private var datalist = mutableListOf<ModelTestPreguntas>()

    fun setListData(data: MutableList<ModelTestPreguntas>) {
        datalist = data
    }
    fun returnListData(): MutableList<ModelTestPreguntas> {
        return datalist;
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): AdapterTestPreguntas.ViewHolderAdapterTestPreguntas {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_test_preguntas, parent, false)
        return ViewHolderAdapterTestPreguntas(view)
    }


    override fun onBindViewHolder(holder: AdapterTestPreguntas.ViewHolderAdapterTestPreguntas, position: Int) {
        val Preguntas = datalist[position]
        holder.bindView(Preguntas)
    }

    inner class ViewHolderAdapterTestPreguntas(itemView: View):RecyclerView.ViewHolder(itemView){

        lateinit var Pregunta: TextView
        lateinit var Respuesta_1: RadioButton
        lateinit var Respuesta_2: RadioButton
        lateinit var Respuesta_3: RadioButton
        lateinit var Respuesta_4: RadioButton
        lateinit var Respuesta_5: RadioButton
        lateinit var aux: RadioButton
        private var tam: Int = 0

        fun bindView(Preguntas: ModelTestPreguntas) {
            Pregunta = itemView.findViewById(R.id.item_preguntas_pregunta)
            Respuesta_1 = itemView.findViewById(R.id.item_preguntas_respuesta_1)
            Respuesta_2 = itemView.findViewById(R.id.item_preguntas_respuesta_2)
            Respuesta_3 = itemView.findViewById(R.id.item_preguntas_respuesta_3)
            Respuesta_4 = itemView.findViewById(R.id.item_preguntas_respuesta_4)
            Respuesta_5 = itemView.findViewById(R.id.item_preguntas_respuesta_5)
            aux = itemView.findViewById(R.id.item_preguntas_aux)

            WriteButton()

            Pregunta.text = "${Preguntas.num_pregunta}. ${Preguntas.pregunta}"

            tam = Preguntas.ListaRespuestas.size

            Respuesta_1.visibility = View.GONE
            Respuesta_2.visibility = View.GONE
            Respuesta_3.visibility = View.GONE
            Respuesta_3.visibility = View.GONE
            Respuesta_4.visibility = View.GONE
            Respuesta_5.visibility = View.GONE

            if (tam == 5){
                Respuesta_1.text = Preguntas.ListaRespuestas[0]
                Respuesta_2.text = Preguntas.ListaRespuestas[1]
                Respuesta_3.text = Preguntas.ListaRespuestas[2]
                Respuesta_4.text = Preguntas.ListaRespuestas[3]
                Respuesta_5.text = Preguntas.ListaRespuestas[4]

                Respuesta_1.visibility = View.VISIBLE
                Respuesta_2.visibility = View.VISIBLE
                Respuesta_3.visibility = View.VISIBLE
                Respuesta_3.visibility = View.VISIBLE
                Respuesta_4.visibility = View.VISIBLE
                Respuesta_5.visibility = View.VISIBLE

            }else if (tam == 4){
                Respuesta_1.text = Preguntas.ListaRespuestas[0]
                Respuesta_2.text = Preguntas.ListaRespuestas[1]
                Respuesta_3.text = Preguntas.ListaRespuestas[2]
                Respuesta_4.text = Preguntas.ListaRespuestas[3]

                Respuesta_1.visibility = View.VISIBLE
                Respuesta_2.visibility = View.VISIBLE
                Respuesta_3.visibility = View.VISIBLE
                Respuesta_3.visibility = View.VISIBLE
                Respuesta_4.visibility = View.VISIBLE
            }else if (tam == 3){
                Respuesta_1.text = Preguntas.ListaRespuestas[0]
                Respuesta_2.text = Preguntas.ListaRespuestas[1]
                Respuesta_3.text = Preguntas.ListaRespuestas[2]

                Respuesta_1.visibility = View.VISIBLE
                Respuesta_2.visibility = View.VISIBLE
                Respuesta_3.visibility = View.VISIBLE
                Respuesta_3.visibility = View.VISIBLE
            }else if (tam == 2){
                Respuesta_1.text = Preguntas.ListaRespuestas[0]
                Respuesta_2.text = Preguntas.ListaRespuestas[1]

                Respuesta_1.visibility = View.VISIBLE
                Respuesta_2.visibility = View.VISIBLE
            }


            Respuesta_1.setOnClickListener { PaintOption(Respuesta_1,1) }
            Respuesta_2.setOnClickListener { PaintOption(Respuesta_2,2) }
            Respuesta_3.setOnClickListener { PaintOption(Respuesta_3,3) }
            Respuesta_4.setOnClickListener { PaintOption(Respuesta_4,4) }
            Respuesta_5.setOnClickListener { PaintOption(Respuesta_5,5) }
        }

        private fun PaintOption(Respuesta:RadioButton,button:Int){

            Respuesta_1.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_2.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_3.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_4.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_5.setTextColor(ContextCompat.getColor(context,R.color.black))

            Respuesta.setTextColor(ContextCompat.getColor(context,R.color.color_respuesta))
                if (context is PreguntasTestActivity){
                    context.listaRespuestas[position] = Respuesta.text.toString()
                    context.listaSelection[position].position1=false
                    context.listaSelection[position].position2=false
                    context.listaSelection[position].position3=false
                    context.listaSelection[position].position4=false
                    context.listaSelection[position].position5=false
                    when(button){
                        1->{context.listaSelection[position].position1=true}
                        2->{context.listaSelection[position].position2=true}
                        3->{context.listaSelection[position].position3=true}
                        4->{context.listaSelection[position].position4=true}
                        5->{context.listaSelection[position].position5=true}
                    }
                }
        }


        private fun WriteButton(){

            Respuesta_1.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_2.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_3.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_4.setTextColor(ContextCompat.getColor(context,R.color.black))
            Respuesta_5.setTextColor(ContextCompat.getColor(context,R.color.black))

            if (context is PreguntasTestActivity){
                aux.isChecked = true
                when(true){
                    context.listaSelection[position].position1 ->{Respuesta_1.isChecked = true}
                    context.listaSelection[position].position2 ->{Respuesta_2.isChecked = true}
                    context.listaSelection[position].position3 ->{Respuesta_3.isChecked = true}
                    context.listaSelection[position].position4 ->{Respuesta_4.isChecked = true}
                    context.listaSelection[position].position5 ->{Respuesta_5.isChecked = true}
                    else ->{aux.isChecked = true}
                }
            }
        }
    }


}