package com.near.educationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LogInActivity : AppCompatActivity() {

    private lateinit var EMAIL: TextInputEditText
    private lateinit var PASS: TextInputEditText
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val iniciarSesion = findViewById<Button>(R.id.iniciar_sesion)
        val recuperar = findViewById<TextView>(R.id.recuperar)
        val registrarse = findViewById<TextView>(R.id.registrar)

        EMAIL = findViewById<TextInputEditText>(R.id.login_correo_texto)
        PASS = findViewById<TextInputEditText>(R.id.login_password_texto)

        iniciarSesion.setOnClickListener {
            ValidateUser()
        }

        recuperar.setOnClickListener {
            val intent = Intent(this, RecuperarActivity::class.java)
                .putExtra("email",EMAIL.text.toString())
            startActivity(intent)
        }

        registrarse.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)

        }
    }

    private fun ValidateUser(){
        var correo = EMAIL.text.toString().replace(" ","")
        var contraseña = PASS.text.toString().replace(" ","")

        if (!correo.isNullOrEmpty() && !contraseña.isNullOrEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                            SavePreferences(correo)
                            val intent = Intent(this, InicioActivity::class.java)
                            startActivity(intent)
                            finish()
                    } else {
                        Toast.makeText(this, "correo o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "llena todos los campos", Toast.LENGTH_SHORT).show()
        }
    }


    private fun SavePreferences(email:String){
        val prefs =getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.apply()
    }

    override fun onBackPressed() {}
}