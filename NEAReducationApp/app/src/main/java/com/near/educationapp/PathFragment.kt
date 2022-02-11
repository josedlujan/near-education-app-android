package com.near.educationapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterPath
import com.near.educationapp.Models.ModelPath

class PathFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterPath
    private var lista = mutableListOf<ModelPath>()
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_path, container, false)

        recycler = view.findViewById(R.id.path_recyclerview_fragment)
        adapter = AdapterPath(view.context)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter

        bd.collection("pathCategorias")
            .get().addOnSuccessListener {
                for (documentos in it) {
                    lista.add(
                        ModelPath(
                        documentos.data.get("id").toString(),
                        documentos.data.get("nombre").toString(),
                        documentos.data.get("activo").toString().toBoolean(),
                            documentos.data.get("num_orden").toString().toInt())
                    )
                }

                var tem: ModelPath
                var bandera = true
                for (i in 0 until lista.size-1){
                    if(bandera == false){
                        break
                    }
                    bandera=false
                    for (j in 0 until lista.size-1){
                        if (lista[j].num_orden > lista[j+1].num_orden){
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


        return view
    }

    companion object {fun newInstance():PathFragment = PathFragment()}
}