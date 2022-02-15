package com.near.educationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    companion object {
        const val TIME_SPLASH_SCREEN = 1200L
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nearlogo = findViewById<ImageView>(R.id.nearlogo)

        val animacion = AnimationUtils.loadAnimation(this,R.anim.splash_screen)
        nearlogo.startAnimation(animacion)

        Handler().postDelayed({
            ReadPrefs()
            finish()
        }, 4000) // 4000 is the delayed time in milliseconds.

    }


    private fun ReadPrefs(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        if(prefs.getString("email", "email").toString().equals("email")){
            startActivity(Intent(this, LogInActivity::class.java))
        }else{
            startActivity(Intent(this, InicioActivity::class.java))
        }
    }


}