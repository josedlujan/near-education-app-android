package com.near.educationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val iniciarSesion = findViewById<Button>(R.id.iniciar_sesion)
        val recuperar = findViewById<TextView>(R.id.recuperar)
        val registrarse = findViewById<TextView>(R.id.registrar)


        iniciarSesion.setOnClickListener {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        recuperar.setOnClickListener {
            val intent = Intent(this, RecuperarActivity::class.java)
            startActivity(intent)
        }

        registrarse.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)

        }
    }
}