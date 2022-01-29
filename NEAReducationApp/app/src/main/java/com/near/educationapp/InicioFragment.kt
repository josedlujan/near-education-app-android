package com.near.educationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterNews
import com.near.educationapp.Models.ModelNews

class InicioFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterNews
    private var lista = mutableListOf<ModelNews>()
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_inicio, container, false)

        recycler = view.findViewById(R.id.inicio_recyclerview)
        adapter = AdapterNews(view.context)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter


        bd.collection("news").get().addOnSuccessListener {
            for (documentos in it) {
                lista.add(
                    ModelNews(
                        documentos.data.get("id").toString(),
                        documentos.data.get("link").toString(),
                        documentos.data.get("title").toString(),
                        documentos.data.get("resumen").toString(),
                        documentos.data.get("date").toString(),
                        documentos.data.get("photo").toString()
                    )
                )
            }

            adapter.setListData(lista)
            adapter.notifyDataSetChanged()
            lista = adapter.returnListData()
        }

        return view
    }

    companion object {
        fun newInstance(): InicioFragment = InicioFragment()
        /*PathFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }*/
    }

}