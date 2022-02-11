package com.near.educationapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterPath
import com.near.educationapp.Adapters.AdapterPathResquisitos
import com.near.educationapp.Models.ModelPath
import java.lang.Exception

class PathActivity : AppCompatActivity() {

    private lateinit var titulo:TextView
    private lateinit var descripcion:TextView
    private lateinit var duracion:TextView
    private lateinit var nivel:TextView
    private lateinit var dificultad:TextView
    private lateinit var button:Button
    private lateinit var lista:ArrayList<String>

    private lateinit var ID_CATEGORIA:String
    private val bd = FirebaseFirestore.getInstance()
    private lateinit var URL:String

    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterPathResquisitos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)
        var Buttonback = findViewById<ImageView>(R.id.path_icon)
        titulo = findViewById(R.id.path_titulo)
        descripcion = findViewById(R.id.path_descripcion)
        duracion = findViewById(R.id.path_duracion)
        nivel = findViewById(R.id.path_nivel)
        dificultad = findViewById(R.id.path_dificultad)
        button = findViewById(R.id.path_button)
        recycler = findViewById(R.id.path_recyclerview)

        val bundle = intent.extras
        ID_CATEGORIA = bundle?.getString("id_categoria").toString()
        titulo.text = bundle?.getString("nombre").toString()

        bd.collection("Path")
            .whereEqualTo("id_categoria","${ID_CATEGORIA}")
            .get().addOnSuccessListener {
                for (documentos in it) {
                    descripcion.text = documentos.data.get("descripcion").toString()
                    dificultad.text = documentos.data.get("dificultad").toString()
                    duracion.text = documentos.data.get("duracion").toString()
                    nivel.text = documentos.data.get("nivel").toString()
                    try {
                        lista =  documentos.data.get("requisitos") as ArrayList<String>
                    }catch (e:Exception){}


                    GetURL(documentos.data.get("link").toString())
                    when(documentos.data.get("dificultad").toString().lowercase()){
                        "easy"->{
                            nivel.setTextColor(ContextCompat.getColor(this,R.color.color_easy))
                            dificultad.setTextColor(ContextCompat.getColor(this,R.color.color_easy))
                        }
                        "medium"->{
                            nivel.setTextColor(ContextCompat.getColor(this,R.color.color_medium))
                            dificultad.setTextColor(ContextCompat.getColor(this,R.color.color_medium))
                        }
                        "hard"->{
                            nivel.setTextColor(ContextCompat.getColor(this,R.color.color_hard))
                            dificultad.setTextColor(ContextCompat.getColor(this,R.color.color_hard))
                        }
                    }
                    break
                }


                try {
                    adapter = AdapterPathResquisitos(this)
                    recycler.layoutManager = LinearLayoutManager(this)
                    recycler.adapter = adapter

                    adapter.setListData(lista)
                    adapter.notifyDataSetChanged()
                    lista = adapter.returnListData()
                }catch (e:Exception){}

            }

        button.setOnClickListener {
            var uri: Uri = Uri.parse(URL)
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        Buttonback.setOnClickListener {
            finish()
        }


    }
    private fun GetURL(url:String){
        URL = url
    }
}