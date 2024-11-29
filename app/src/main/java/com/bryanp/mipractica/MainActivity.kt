package com.bryanp.mipractica

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activar el modo de borde a borde (Edge to Edge)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configurar el listener para los márgenes de las barras del sistema (barra de estado y barra de navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos de la interfaz
        val numero1EditText: EditText = findViewById(R.id.numero1)
        val numero2EditText: EditText = findViewById(R.id.numero2)
        val resultadoTextView: TextView = findViewById(R.id.resultado)
        val calcularButton: Button = findViewById(R.id.calcular)
        val seleccionSpinner: Spinner = findViewById(R.id.seleccion)

        // Array de operaciones disponibles (declarado en res/values/strings.xml)
        val operaciones = resources.getStringArray(R.array.operaciones)

        // Configuración del Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            operaciones
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        seleccionSpinner.adapter = adapter

        // Listener para el botón Calcular
        calcularButton.setOnClickListener {
            val numero1Text = numero1EditText.text.toString()
            val numero2Text = numero2EditText.text.toString()

            if (numero1Text.isNotEmpty() && numero2Text.isNotEmpty()) {
                val numero1 = numero1Text.toDouble()
                val numero2 = numero2Text.toDouble()
                val operacion = seleccionSpinner.selectedItem.toString()

                val resultado = when (operacion) {
                    "Add" -> numero1 + numero2
                    "Subtract" -> numero1 - numero2
                    "Multiply" -> numero1 * numero2
                    "Divide" -> {
                        if (numero2 != 0.0) numero1 / numero2 else "Error: Division by 0"
                    }
                    "Sumar" -> numero1 + numero2
                    "Restar" -> numero1 - numero2
                    "Multiplicar" -> numero1 * numero2
                    "Dividir" -> {
                        if (numero2 != 0.0) numero1 / numero2 else "Error: Division by 0"
                    }
                    else -> "Invalid operation"
                }

                // Mostrar el resultado
                resultadoTextView.text = resultado.toString()
            } else {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
