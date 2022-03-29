package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarActivity : AppCompatActivity() {

    private val bd = FirebaseFirestore.getInstance()
    private lateinit var NAME: TextInputEditText
    private lateinit var EMAIL: TextInputEditText
    private lateinit var PASS: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        val ButtonRegistrar = findViewById<Button>(R.id.registrar_boton)
        NAME = findViewById<TextInputEditText>(R.id.registrar_nombre_campo)
        EMAIL = findViewById<TextInputEditText>(R.id.registrar_correo_campo)
        PASS = findViewById<TextInputEditText>(R.id.registrar_password_campo)

        ButtonRegistrar.setOnClickListener {
            ValidateData()
        }

    }



    private fun ValidateData() {
        var correo = EMAIL.text.toString().replace(" ","")
        if (!correo.isNullOrEmpty()){
            bd.collection("users").document(correo).get().addOnSuccessListener {
                if (it.get("email").toString().equals("null")){
                    RegisterUser(correo)
                }else{
                    Toast.makeText(this, "el correo ya esta ocupado", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }


    private fun RegisterUser(correo: String) {
        var nombre = NAME.text.toString().replace(" ","")
        var contraseña = PASS.text.toString().replace(" ","")

        if (contraseña.isNullOrEmpty() || nombre.isNullOrEmpty()){
            Toast.makeText(this, "llena todos los campos", Toast.LENGTH_SHORT).show()
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener{
                if (it.isSuccessful) {
                    bd.collection("users").document(correo).set(
                        hashMapOf(
                            "email" to correo,
                            "name" to nombre))
                    Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "ocurrio un error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


}