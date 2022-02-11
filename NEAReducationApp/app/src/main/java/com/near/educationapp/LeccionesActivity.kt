package com.near.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.near.educationapp.Models.ModelLecciones

class LeccionesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecciones)

        var titulo:TextView = findViewById(R.id.lecciones_titulo)
        var autor:TextView = findViewById(R.id.lecciones_autor)
        var fecha:TextView = findViewById(R.id.lecciones_fecha)
        var articulo:TextView = findViewById(R.id.lecciones_articulo)
        var foto:ImageView = findViewById(R.id.lecciones_foto)

        val bundle = intent.extras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = bundle?.getString("titulo").toString()


        titulo.text = bundle?.getString("titulo").toString()
        autor.text = "${bundle?.getString("autor").toString()} - "
        fecha.text = FormatoFecha(bundle?.getString("fecha").toString())
        articulo.text = bundle?.getString("articulo").toString()

        Glide.with(this).load(bundle?.getString("foto").toString()).into(foto)

    }

    private fun FormatoFecha(data:String):String{
        val Mylist: List<String> = data.split("/")
        return when(Mylist[1].toInt()){
            1->{"${Mylist[0]} de enero ${Mylist[2]}" }
            2->{"${Mylist[0]} de febrero ${Mylist[2]}" }
            3->{"${Mylist[0]} de marzo ${Mylist[2]}" }
            4->{"${Mylist[0]} de abril ${Mylist[2]}" }
            5->{"${Mylist[0]} de mayo ${Mylist[2]}" }
            6->{"${Mylist[0]} de junio ${Mylist[2]}" }
            7->{"${Mylist[0]} de julio ${Mylist[2]}" }
            8->{"${Mylist[0]} de agosto ${Mylist[2]}" }
            9->{"${Mylist[0]} de septiembre ${Mylist[2]}" }
            10->{"${Mylist[0]} de octubre ${Mylist[2]}" }
            11->{"${Mylist[0]} de noviembre ${Mylist[2]}" }
            12->{"${Mylist[0]} de diciembre ${Mylist[2]}" }
            else->{data}
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}