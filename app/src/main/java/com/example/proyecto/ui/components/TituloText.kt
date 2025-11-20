package com.example.proyecto.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TituloText(titulo: String) {
    Text(
        text = titulo,
        style = MaterialTheme.typography.headlineLarge.copy(
            fontSize = 40.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Black, // Muy grueso
            color = Color(0xFF35FA02), // Morado gaming
            letterSpacing = 1.5.sp // Más separación entre letras

            )

    )
}