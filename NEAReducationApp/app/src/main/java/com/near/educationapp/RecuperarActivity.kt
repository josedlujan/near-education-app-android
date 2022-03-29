package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RecuperarActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private lateinit var EMAIL: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)

        EMAIL = findViewById<TextInputEditText>(R.id.recuperar_texto_campo)
        val ButtonRecuperar = findViewById<Button>(R.id.recuperar_boton)

        val bundle = intent.extras
        var correo = bundle?.getString("email").toString()
        EMAIL.setText(correo)


        ButtonRecuperar.setOnClickListener {
            var correo = EMAIL.text.toString().replace(" ","")
            if (!correo.isNullOrEmpty()){
                auth.setLanguageCode("es")
                auth.sendPasswordResetEmail(correo).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "hemos mandado un correo de recuperacion", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this, "algo salio mal", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "ingresa el correo", Toast.LENGTH_SHORT).show()
            }
        }


    }
}