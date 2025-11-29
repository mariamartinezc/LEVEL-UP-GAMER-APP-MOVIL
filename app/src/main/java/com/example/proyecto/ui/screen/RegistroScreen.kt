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
import com.example.proyecto.model.Usuario
import com.example.proyecto.ui.components.BotonLevelUp
import com.example.proyecto.ui.components.CampoTexto
import com.example.proyecto.ui.components.TituloText
import com.example.proyecto.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegistroScreen(navController: NavController){

    var nombre by remember { mutableStateOf("") }
    var contraseña by remember{mutableStateOf("")}
    var confirmarContraseña by remember { mutableStateOf("") }
    var correo by remember{mutableStateOf("")}
    var direccion by remember {mutableStateOf("")}
    var telefono by remember {mutableStateOf("")}
    var mensajeError by remember {mutableStateOf("")  }

    val viewModel: LoginViewModel = viewModel ()

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
            TituloText("Registrar Cuenta ")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Unete a Level-Up",
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


            Spacer(modifier = Modifier.height(32.dp))

            // Campos del formulario
            CampoTexto(
                valor = nombre,
                onValorCambio = {nombre = it},
                etiqueta = "Nombre de usuario *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = correo,
                onValorCambio = {correo = it},
                etiqueta = "Correo @levelup.cl *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = direccion,
                onValorCambio = {direccion = it},
                etiqueta = "Direccion"
            )

            Spacer(modifier = Modifier.height(16.dp))
            CampoTexto(
                valor = telefono,
                onValorCambio = {telefono = it},
                etiqueta = "Telefono"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = contraseña,
                onValorCambio = {contraseña = it},
                etiqueta = "Contraseña *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CampoTexto(
                valor = confirmarContraseña,
                onValorCambio = {confirmarContraseña = it},
                etiqueta = "Confirmar contraseña *"
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonLevelUp(
                texto = "Guardar",
                onClickAccion = {
                    if (nombre.isBlank() || contraseña.isBlank() || correo.isBlank()){
                        mensajeError = "Complete todos los campos (*) obligatorios"
                        return@BotonLevelUp
                    }
                    if (!correo.contains("@levelup.cl")) {
                        mensajeError = "Ingresa un correo electrónico válido"
                        return@BotonLevelUp
                    }

                    if (contraseña != confirmarContraseña) {
                        mensajeError = "Las contraseñas no coinciden"
                        return@BotonLevelUp
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val nuevoUsuario = Usuario(
                                nombre = nombre,
                                contrasena = contraseña,
                                correo = correo,
                                telefono = telefono,
                                direccion = direccion
                            )

                            val registroExitoso = viewModel.registroUsuario(nuevoUsuario)

                            withContext(Dispatchers.Main) {
                                if (registroExitoso) {
                                    navController.navigate("login")
                                } else {
                                    mensajeError = "El usuario ya existe"
                                }
                            }

                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                mensajeError = "Error al registrar: ${e.message}"
                            }
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            BotonLevelUp(
                texto = "Volver",
                onClickAccion = {
                    navController.navigate("login")
                }
            )
        }
    }
}