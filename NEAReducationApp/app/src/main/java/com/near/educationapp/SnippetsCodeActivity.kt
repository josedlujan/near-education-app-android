package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import thereisnospon.codeview.CodeView
import thereisnospon.codeview.CodeViewTheme

class SnippetsCodeActivity : AppCompatActivity() {


    private var codigo:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snippets_code)

        val bundle = intent.extras
        codigo = bundle?.getString("codigo").toString()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Codigo"

        var tituto:TextView = findViewById(R.id.SnippetsCode_titulo)
        tituto.text = bundle?.getString("titulo").toString()

        var codeview:CodeView = findViewById(R.id.codeview)
        codeview.setTheme(CodeViewTheme.ATELIER_CAVE_LIGHT)


        val oldValue = "!salto"
        val newValue = "\n"
        val output = codigo.replace(oldValue, newValue)

        codeview.showCode(output)


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}