package com.near.educationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdpterSnippets
import com.near.educationapp.Models.ModelSnippets


class SnippetsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var recycler: RecyclerView
    private lateinit var adapter:AdpterSnippets
    private var lista = mutableListOf<ModelSnippets>()
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_snippets, container, false)

        recycler = view.findViewById(R.id.snippets_recyclerview)
        adapter = AdpterSnippets(view.context)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter

        lista.clear()
        bd.collection("SnippetsCategorias").orderBy("name")
            .get().addOnSuccessListener {
            for (documentos in it) {
                lista.add(
                    ModelSnippets(
                        documentos.data.get("id").toString(),
                        documentos.data.get("name").toString(),
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

    companion object {fun newInstance(): SnippetsFragment = SnippetsFragment()}
}