package com.unitec.apptextoavoz2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener { //Interfaz depues de la herencia
    //Objeto intermediario entre mi app y TextToSpeech
    private var tts:TextToSpeech?=null //Ejecutar solo cuando exista
    //codigo de peticion con int, para garantizar que tts se inicio
    private val CODIGO_PETICION=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Iniciar la var tts
        tts= TextToSpeech(this,this)
        //Mensaje de bienvenida
        ////A daemon thread is a low-priority thread in java which runs in the background
        //Alt + Enter para importar shedule lib
        //!! para garantizar que no esta en null
        Timer("Bienvenido",false).schedule(1000){
            tts!!.speak(
                    "Hola, bienvenido",
                    TextToSpeech.QUEUE_FLUSH, //eliminar mensajes en fila
                    null,
                    ""
            )
        }

    }

    override fun onInit(estado: Int) {
        //Este metodo inicia la congig al inicio. (Idioma)
        if(estado==TextToSpeech.SUCCESS){
            var local=Locale("spa","MEX")
            //var para ver va bien el proceso
            val resultado=tts!!.setLanguage(local)
            if(resultado==TextToSpeech.LANG_MISSING_DATA){
                Log.i("MALO","No funciono el codigo")//.i lo manda en info en Logcat
            }
        }
    }
}