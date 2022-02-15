package com.near.educationapp


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.near.educationapp.Adapters.AdapterLevels
import com.near.educationapp.Adapters.AdapterTestTitle
import com.near.educationapp.Models.ModelLevels
import com.near.educationapp.Models.ModelTestPreguntas

class LevesActivity : AppCompatActivity() {

    var ID_TEST:String = ""
    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterLevels
    private var lista = mutableListOf<ModelLevels>()
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leves)

        val bundle = intent.extras
        ID_TEST = bundle?.getString("id").toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = bundle?.getString("name").toString()

        recycler = findViewById(R.id.levels_recyclerview)
        adapter = AdapterLevels(this,getCorreo())
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        CargarNiveles()
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun getCorreo(): String {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        var email = prefs.getString("email", "null").toString()
        return email
    }

    override fun onRestart() {
        super.onRestart()
        CargarNiveles()
    }


    private fun CargarNiveles(){
        lista.clear()
        bd.collection("TestNiveles")
            .whereEqualTo("id_categoria","${ID_TEST}")
            .get().addOnSuccessListener {
                for (documentos in it) {
                    lista.add(ModelLevels(
                        documentos.data.get("id").toString(),
                        documentos.data.get("id_categoria").toString(),
                        documentos.data.get("nivel_number").toString(),
                        documentos.data.get("nivel_texto").toString()))

                }

                var tem:ModelLevels
                var bandera = true
                for (i in 0 until lista.size-1){
                    if(bandera == false){
                        break
                    }
                    bandera=false
                    for (j in 0 until lista.size-1){
                        if (lista[j].nivel_number > lista[j+1].nivel_number){
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
            }

    }

}
