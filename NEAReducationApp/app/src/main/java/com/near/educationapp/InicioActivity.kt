package com.near.educationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class InicioActivity : AppCompatActivity() {

    val inicioFragment = InicioFragment.newInstance()
    val pathFragment = PathFragment.newInstance()
    val snippetsFragment = SnippetsFragment.newInstance()
    val leccionesFragment = LeccionesFragment.newInstance()
    val testFragment = TestFragment.newInstance()
    val moreFragment = MoreFragment.newInstance()
    var position:Int = 1

    private lateinit var fondo:RelativeLayout
    private lateinit var message:RelativeLayout
    private lateinit var button_cancel:TextView
    private lateinit var button_ok:TextView

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fondo = findViewById(R.id.Home_fondo)
        message = findViewById(R.id.Home_message)
        button_cancel = findViewById(R.id.Home_button_cancel)
        button_ok = findViewById(R.id.Home_button_ok)

        button_cancel.setOnClickListener {
            HideMessageExit()
        }
        button_ok.setOnClickListener {
            LogOut()
        }

        toolbar = supportActionBar!!
        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)

        bottomNav.setOnItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.inicio->{
                    toolbar.title = "Inicio"
                    changeFragment(inicioFragment)
                    position = 1
                    return@setOnItemSelectedListener true
                }
                R.id.path->{
                    toolbar.title = "Path"
                    changeFragment(pathFragment)
                    position = 2
                    return@setOnItemSelectedListener true
                }
                R.id.snippets->{
                    toolbar.title = "Snippets"
                    changeFragment(snippetsFragment)
                    position = 3
                    return@setOnItemSelectedListener true
                }
                R.id.lecciones->{
                    toolbar.title = "Lecciones"
                    changeFragment(leccionesFragment)
                    position = 4
                    return@setOnItemSelectedListener true
                }
                R.id.more->{
                    toolbar.title = "More"
                    changeFragment(moreFragment)
                    position = 5
                    return@setOnItemSelectedListener true
                }


            }
            false
        }

        toolbar.title = "Inicio"
        changeFragment(inicioFragment)

    }

    private fun changeFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedor,fragment)
        transaction.commit()
    }

    fun ShowTest(){
        toolbar.title = "Test"
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedor,testFragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (position == 5){
            if (fondo.visibility == View.VISIBLE){
                HideMessageExit()
            }else{
                toolbar.title = "More"
                changeFragment(moreFragment)
            }
        }
    }

    private fun LogOut(){
        DeletePrefs()
        startActivity(Intent(this, LogInActivity::class.java))
        finish()
    }

    private fun DeletePrefs(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
    }

    fun ShowMessageExit(){
        fondo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        fondo.visibility = View.VISIBLE

        message.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        message.visibility = View.VISIBLE
    }


    private fun HideMessageExit(){
        fondo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out))
        fondo.visibility = View.GONE

        message.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out))
        message.visibility = View.GONE
    }



}