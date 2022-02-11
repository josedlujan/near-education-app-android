package com.near.educationapp.Models

data class ModelTestPreguntas (
    var id_categoria:String,
    var id_nivel:String,
    var num_pregunta:Int,
    var pregunta:String,
    var respuesta_1:String,
    var respuesta_2:String,
    var respuesta_3:String,
    var respuesta_4:String,
    var respuesta_correcta:String,
    var ListaRespuestas:ArrayList<String>)