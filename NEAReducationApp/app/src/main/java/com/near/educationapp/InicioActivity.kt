package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class InicioActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = supportActionBar!!
        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)

        bottomNav.setOnItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.inicio->{
                    toolbar.title = "Inicio"
                    val inicioFragment = InicioFragment.newInstance()
                    changeFragment(inicioFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.path->{
                    toolbar.title = "Path"
                    val pathFragment = PathFragment.newInstance()
                    changeFragment(pathFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.snippets->{
                    toolbar.title = "Snippets"
                    val snippetsFragment = SnippetsFragment.newInstance()
                    changeFragment(snippetsFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.lecciones->{
                    toolbar.title = "Lecciones"
                    val leccionesFragment = LeccionesFragment.newInstance()
                    changeFragment(leccionesFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.test->{
                    toolbar.title = "Tests"
                    val testFragment = TestFragment.newInstance()
                    changeFragment(testFragment)
                    return@setOnItemSelectedListener true
                }


            }
            false
        }

        toolbar.title = "Inicio"

    }

    private fun changeFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedor,fragment)
        transaction.commit()
    }


}