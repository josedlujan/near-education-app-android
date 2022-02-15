package com.near.educationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.near.educationapp.Adapters.AdapterTestTitle
import com.near.educationapp.Models.ModelNews
import com.near.educationapp.Models.ModelTestTitle

class TestFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterTestTitle
    private var lista = mutableListOf<ModelTestTitle>()
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_test, container, false)

        lista.clear()
        recycler = view.findViewById(R.id.test_recyclerview)
        adapter = AdapterTestTitle(view.context)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter

        bd.collection("TestCategorias").get().addOnSuccessListener {
            for (documentos in it) {
                lista.add(
                    ModelTestTitle(
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

    companion object {
        fun newInstance():TestFragment=TestFragment()
    }
}