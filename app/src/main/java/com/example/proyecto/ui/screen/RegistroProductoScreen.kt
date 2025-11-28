package com.example.proyecto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.model.Producto
import com.example.proyecto.ui.components.BotonLevelUp
import com.example.proyecto.ui.components.CampoTexto
import com.example.proyecto.ui.components.TituloText
import com.example.proyecto.viewmodel.ProductoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun RegistroProductoScreen(navController: NavController){
    var nombreProducto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf("true") }
    var imagen by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    val viewModel: ProductoViewModel = viewModel ()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()

    ){// Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Fondo LevelUp login",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.7f)
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(24.dp)
        ){
            // Título
            TituloText("Registrar Producto ")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Productos Level-Up",
                color = Color.Blue,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (mensajeError.isNotEmpty()) {
                Text(
                    text = mensajeError,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Campos del formulario
            CampoTexto(
                valor = nombreProducto,
                onValorCambio = {nombreProducto = it},
                etiqueta = "Nombre Producto *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = descripcion,
                onValorCambio = {descripcion = it},
                etiqueta = "descripcion *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = precio,
                onValorCambio = {precio = it},
                etiqueta = "Precio 1000.00 *"
            )

            Spacer(modifier = Modifier.height(16.dp))
            CampoTexto(
                valor = stock,
                onValorCambio = {stock = it},
                etiqueta = "Stock *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = categoria,
                onValorCambio = {categoria = it},
                etiqueta = "Categoria *"
            )
            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = activo,
                onValorCambio = {activo = it},
                etiqueta = "Activo true/false tg"
            )



            Spacer(modifier = Modifier.height(24.dp))

            BotonLevelUp(
                texto = "Guardar Producto",
                onClickAccion = {
                    if (nombreProducto.isBlank() || descripcion.isBlank() || precio.isBlank() ||
                        stock.isBlank() || categoria.isBlank()) {
                        mensajeError = "Complete todos los campos obligatorios"
                        return@BotonLevelUp
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val nuevoProducto = Producto(
                                nombreProducto = nombreProducto,
                                descripcion = descripcion,
                                precio = precio.toDouble(),
                                stock = stock.toInt(),
                                categoria = categoria,
                                activo = activo.toBoolean(),
                                imagen = if (imagen.isBlank()) "logocircular" else imagen
                            )

                            val exito = viewModel.guardarProducto(nuevoProducto)

                            withContext(Dispatchers.Main) {
                                if (exito) {
                                    mensajeError = "Producto guardado exitosamente"
                                    // Limpiar campos
                                    nombreProducto = ""
                                    descripcion = ""
                                    precio = ""
                                    stock = ""
                                    categoria = ""
                                    activo = "true"
                                    imagen = ""
                                } else {
                                    mensajeError = "Error al guardar producto"
                                }
                            }
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                mensajeError = "Error: ${e.message}"
                            }
                        }
                    }
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            BotonLevelUp(
                texto = "Volver",
                onClickAccion = {
                    navController.popBackStack() //popBackStack cierra la pantalla actual y vuelve a la anterior
                }
            )
        }
    }
}