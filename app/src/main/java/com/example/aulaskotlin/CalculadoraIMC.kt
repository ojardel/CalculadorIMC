package com.example.aulaskotlin

class CalculadoraImc {

    fun converter(texto: String): Double? =
        texto.replace(",", ".").toDoubleOrNull()

    fun calcular(pesoKg: Double, alturaM: Double): Double =
        pesoKg / (alturaM * alturaM)

    fun classificar(imc: Double): String = when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        imc < 35.0 -> "Obesidade grau I"
        imc < 40.0 -> "Obesidade grau II"
        else -> "Obesidade grau III"
    }
}