package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
        if (!EMAIL.text.isNullOrEmpty()){
            bd.collection("users").document(EMAIL.text.toString()).get().addOnSuccessListener {
                if (it.get("email").toString().equals("null")){
                    RegisterUser()
                }else{
                    Toast.makeText(this, "el correo ya esta ocupado", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }


    private fun RegisterUser(){
        if (PASS.text.isNullOrEmpty() || NAME.text.isNullOrEmpty()){
            Toast.makeText(this, "llena todos los campos", Toast.LENGTH_SHORT).show()
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(EMAIL.text.toString(),PASS.text.toString()).addOnCompleteListener{
                if (it.isSuccessful) {
                    bd.collection("users").document(EMAIL.text.toString()).set(
                        hashMapOf(
                            "email" to EMAIL.text.toString(),
                            "name" to NAME.text.toString()))
                    Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "ocurrio un error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }


}