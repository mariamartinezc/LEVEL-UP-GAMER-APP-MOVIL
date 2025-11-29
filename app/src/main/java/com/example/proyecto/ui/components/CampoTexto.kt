package com.example.proyecto.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
fun CampoTexto(
    valor: String,
    onValorCambio: (String) -> Unit,
    etiqueta: String,
    esError: Boolean = false,
    mensajeError: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    //ANIMACIÓN DE COLOR - ColorState
    val colorBorde by animateColorAsState(
        targetValue = if (esError) Color.Red else Color(0xFF6C2C94),
        animationSpec = tween(durationMillis = 300),
        label = "colorBorde"
    )

    val colorTexto by animateColorAsState(
        targetValue = if (esError) Color.Red else Color(0xFF030000),
        animationSpec = tween(durationMillis = 300),
        label = "colorTexto"
    )

    Column(modifier = modifier) {
        OutlinedTextField(
            value = valor,
            onValueChange = onValorCambio,
            label = {
                Text(
                    text = etiqueta,
                    color = colorTexto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            isError = esError,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorBorde,
                unfocusedIndicatorColor = colorBorde,
                errorIndicatorColor = Color.Red,
                cursorColor = Color(0xFF6A1B9A)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        //AGREGAR: Mensaje de error con animación
        AnimatedVisibility(
            visible = esError && mensajeError.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = mensajeError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}