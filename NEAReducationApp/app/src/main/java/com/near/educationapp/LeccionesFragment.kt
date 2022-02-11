package com.near.educationapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterLecciones
import com.near.educationapp.Adapters.AdapterLevels
import com.near.educationapp.Models.ModelLecciones
import com.near.educationapp.Models.ModelLevels

class LeccionesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterLecciones
    private var lista = mutableListOf<ModelLecciones>()
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_lecciones, container, false)

        recycler = view.findViewById(R.id.lecciones_recyclerview)
        adapter = AdapterLecciones(view.context)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter
        bd.collection("Lecciones")
            .get().addOnSuccessListener {
                for (documentos in it) {
                    lista.add(ModelLecciones(
                        documentos.data.get("id").toString(),
                        documentos.data.get("titulo").toString(),
                        documentos.data.get("autor").toString(),
                        documentos.data.get("articulo").toString(),
                        documentos.data.get("fecha").toString(),
                        documentos.data.get("foto").toString()))
                }

                adapter.setListData(lista)
                adapter.notifyDataSetChanged()
                lista = adapter.returnListData()
            }


        return view
    }

    companion object { fun newInstance():LeccionesFragment = LeccionesFragment() }
}