package com.near.educationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterLevels
import com.near.educationapp.Adapters.AdapterTestPreguntas
import com.near.educationapp.Models.ModelLevels
import com.near.educationapp.Models.ModelPositions
import com.near.educationapp.Models.ModelTestPreguntas
import com.near.educationapp.Models.ModelTestTitle
import java.lang.Exception

class PreguntasTestActivity : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()
    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterTestPreguntas
    private var lista = mutableListOf<ModelTestPreguntas>()
    var listaRespuestas = ArrayList<String>()
    private var id_categoria:String = ""
    private var id_nivel:String = ""
    private var nivel_number:String = ""
    var listaSelection = mutableListOf<ModelPositions>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas_test)

        val bundle = intent.extras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Nivel ${bundle?.getString("nivel").toString()}"

        id_categoria = bundle?.getString("id_categoria").toString()
        id_nivel = bundle?.getString("id_nivel").toString()
        nivel_number = bundle?.getString("nivel").toString()

        var button = findViewById<Button>(R.id.button_test)
        button.setOnClickListener {

            var aciertos:Int = 0
            var errores:Int = 0

            var termino:Boolean = true
            for (i in listaRespuestas.indices){
                    if(listaRespuestas[i].equals("null")){
                        termino = false
                        break
                    }else{
                        if (listaRespuestas[i].equals(lista[i].respuesta_correcta)){
                            aciertos++
                        }else{
                            errores++
                        }
                    }
            }
            if (termino){
                startActivity(
                    Intent(this,TestCalificacionActivity::class.java)
                    .putExtra("aciertos","${aciertos}")
                    .putExtra("errores","${errores}")
                    .putExtra("id_categoria",id_categoria)
                    .putExtra("id_nivel",id_nivel)
                    .putExtra("nivel",nivel_number))
                finish()
            }

        }
        recycler = findViewById(R.id.TestPreguntas_recyclerview)
        adapter = AdapterTestPreguntas(this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        bd.collection("TestPreguntas")
            .whereEqualTo("id_categoria","${id_categoria}")
            .whereEqualTo("id_nivel","${id_nivel}")
            .get().addOnSuccessListener {
            for (documentos in it) {
                lista.add(ModelTestPreguntas(
                    documentos.data.get("id_categoria").toString(),
                    documentos.data.get("id_nivel").toString(),
                    documentos.data.get("num_pregunta").toString().toInt(),
                    documentos.data.get("pregunta").toString(),
                    documentos.data.get("respuesta_1").toString(),
                    documentos.data.get("respuesta_2").toString(),
                    documentos.data.get("respuesta_3").toString(),
                    documentos.data.get("respuesta_4").toString(),
                    documentos.data.get("respuesta_correcta").toString(),
                    documentos.data.get("Respuestas") as ArrayList<String>))

                listaRespuestas.add("null")
                listaSelection.add(ModelPositions(false,false,false,false,false))
            }
            var tem:ModelTestPreguntas
            var bandera = true
            for (i in 0 until lista.size-1){
                if(bandera == false){
                    break
                }
                bandera=false
                for (j in 0 until lista.size-1){
                    if (lista[j].num_pregunta > lista[j+1].num_pregunta){
                        bandera=true
                        tem = lista[j]
                        lista[j] = lista[j+1]
                        lista[j+1]=tem
                    }
                }
            }
            adapter.setListData(lista)
            adapter.notifyDataSetChanged()
            lista = adapter.returnListData()
        }.addOnFailureListener{}
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}