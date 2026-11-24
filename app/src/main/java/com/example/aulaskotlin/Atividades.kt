package com.example.aulaskotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val calculadora = CalculadoraImc()

    private lateinit var campoPeso: EditText
    private lateinit var campoAltura: EditText
    private lateinit var textoErro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tipoNumero = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        campoPeso = EditText(this).apply {
            hint = "Informe seu peso em kg"
            inputType = tipoNumero
        }
        campoAltura = EditText(this).apply {
            hint = "Informe sua altura em metros (ex.: 1,68)"
            inputType = tipoNumero
        }
        textoErro = TextView(this).apply { textSize = 16f }

        val botaoCalcular = Button(this).apply {
            text = "Calcular IMC"
            setOnClickListener { calcular() }
        }
        val botaoLimpar = Button(this).apply {
            text = "Limpar campos"
            setOnClickListener { limpar() }
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
            addView(campoPeso)
            addView(campoAltura)
            addView(botaoCalcular)
            addView(botaoLimpar)
            addView(textoErro)
        }
        setContentView(layout)
    }

    private fun calcular() {
        val peso = calculadora.converter(campoPeso.text.toString())
        var altura = calculadora.converter(campoAltura.text.toString())

        if (peso == null || altura == null || peso <= 0 || altura <= 0) {
            textoErro.text = "Preencha o peso e a altura corretamente."
            return
        }
        // Caso a altura tenha sido informada em cm (ex.: 168), transforma em metros
        if (altura > 3) altura /= 100

        val imc = calculadora.calcular(peso, altura)
        val classificacao = calculadora.classificar(imc)

        textoErro.text = ""

        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra("imc", imc)
            putExtra("classificacao", classificacao)
        }
        startActivity(intent)
    }

    private fun limpar() {
        campoPeso.text.clear()
        campoAltura.text.clear()
        textoErro.text = ""
        campoPeso.requestFocus()
    }
}