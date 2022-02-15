package com.near.educationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class MoreFragment : Fragment() {



    private lateinit var ButtonTest:LinearLayout
    private lateinit var ButtonLogOut:LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_more, container, false)

        ButtonLogOut = view.findViewById(R.id.more_logOut)
        ButtonTest = view.findViewById(R.id.more_test)

        ButtonTest.setOnClickListener {
            (context as InicioActivity).ShowTest()
        }

        ButtonLogOut.setOnClickListener {
            (context as InicioActivity).ShowMessageExit()
        }

        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {super.onCreate(savedInstanceState)}
    companion object {fun newInstance():MoreFragment=MoreFragment()}
}