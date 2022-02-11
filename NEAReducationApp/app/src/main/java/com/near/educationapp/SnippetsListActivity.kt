package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterSnippetsList
import com.near.educationapp.Adapters.AdpterSnippets
import com.near.educationapp.Models.ModelLevels
import com.near.educationapp.Models.ModelSnippets
import com.near.educationapp.Models.ModelSnippetsList

class SnippetsListActivity : AppCompatActivity() {

    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterSnippetsList
    private var lista = mutableListOf<ModelSnippetsList>()
    private val bd = FirebaseFirestore.getInstance()

    private var id:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snippets_list)

        val bundle = intent.extras
        id = bundle?.getString("id").toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = bundle?.getString("name").toString()


        recycler = findViewById(R.id.snippets_list_recyclerview)
        adapter = AdapterSnippetsList(this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter


        bd.collection("Snippets").whereEqualTo("id_categoria",id)
            .get().addOnSuccessListener {
                for (documentos in it) {
                    lista.add(
                        ModelSnippetsList(
                            documentos.data.get("id_categoria").toString(),
                            documentos.data.get("id").toString(),
                            documentos.data.get("titulo").toString(),
                            documentos.data.get("codigo").toString(),
                            documentos.data.get("num_snippets").toString().toInt()
                        )
                    )
                }



                var tem: ModelSnippetsList
                var bandera = true
                for (i in 0 until lista.size-1){
                    if(bandera == false){
                        break
                    }
                    bandera=false
                    for (j in 0 until lista.size-1){
                        if (lista[j].num_snippets > lista[j+1].num_snippets){
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


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}