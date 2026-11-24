package com.example.aulaskotlin

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ResultadoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imc = intent.getDoubleExtra("imc", 0.0)
        val classificacao = intent.getStringExtra("classificacao") ?: ""

        val textoImc = TextView(this).apply {
            text = "Resultado do IMC: %.2f".format(imc)
            textSize = 28f
        }
        val textoClassificacao = TextView(this).apply {
            text = classificacao
            textSize = 22f
        }
        val botaoVoltar = Button(this).apply {
            text = "Nova consulta"
            setOnClickListener { finish() }
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
            addView(textoImc)
            addView(textoClassificacao)
            addView(botaoVoltar)
        }
        setContentView(layout)
    }
}