package com.near.educationapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class TestCalificacionActivity : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()
    private var aciertos:Int = 0
    private var errores:Int = 0
    private var id_categoria:String = ""
    private var id_nivel:String = ""
    private var nivel:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_calificacion)

        val bundle = intent.extras

        aciertos = bundle?.getString("aciertos").toString().toInt()
        errores = bundle?.getString("errores").toString().toInt()
        id_categoria = bundle?.getString("id_categoria").toString()
        id_nivel = bundle?.getString("id_nivel").toString()
        nivel = bundle?.getString("nivel").toString()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Nivel ${bundle?.getString("nivel").toString()}"


        var TexViewErrorres = findViewById<TextView>(R.id.Test_Calificacion_errores)
        var TexViewAciertos = findViewById<TextView>(R.id.Test_Calificacion_aciertos)
        var button = findViewById<Button>(R.id.Test_Calificacion_button)

        TexViewAciertos.text = "${aciertos}"
        TexViewErrorres.text = "${errores}"

        button.setOnClickListener {
            bd.collection("TestCalificaciones")
                .document("${getCorreo()}_${id_categoria}_${id_nivel}").set(
                hashMapOf(
                    "id_categoria" to id_categoria,
                    "id_nivel" to id_nivel,
                    "email" to getCorreo(),
                    "correctos" to aciertos,
                    "errores" to errores

                ))

            Toast.makeText(this, "Resultado Guardado", Toast.LENGTH_SHORT).show()
        }

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
}