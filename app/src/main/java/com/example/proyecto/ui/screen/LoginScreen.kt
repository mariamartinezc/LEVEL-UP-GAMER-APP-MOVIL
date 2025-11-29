package com.example.proyecto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.ui.components.BotonLevelUp
import com.example.proyecto.ui.components.CampoTexto
import com.example.proyecto.ui.components.TituloText
import com.example.proyecto.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(navController: NavController) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val viewModel: LoginViewModel = viewModel()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Fondo LevelUp login",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.9f)
        )

        //Contenido principal
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            //Título
            TituloText("LEVEL UP")

            Spacer(modifier = Modifier.height(8.dp))

            //Subtítulo
            Text(
                text = "GAMER STORE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            //Campos de texto
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(280.dp)
            ) {
                CampoTexto(
                    valor = usuario,
                    onValorCambio = { usuario = it
                        //Limpiar error cuando el usuario empiece a escribir
                        if (showError) showError = false
                    },
                    etiqueta = "Usuario",
                    esError = showError && usuario.isBlank(),
                    mensajeError = if (showError && usuario.isBlank()) "Usuario requerido" else ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                CampoTexto(
                    valor = contrasena,
                    onValorCambio = { contrasena = it
                        //Limpiar error cuando el usuario empiece a escribir
                        if (showError) showError = false
                    },
                    etiqueta = "Contraseña",
                    visualTransformation = PasswordVisualTransformation(),
                    esError = showError && contrasena.isBlank(),
                    mensajeError = if (showError && contrasena.isBlank()) "Contraseña requerida" else ""
                )

                Spacer(modifier = Modifier.height(24.dp))

                //Mensaje
                if (mensaje.isNotEmpty()) {
                    Text(
                        text = mensaje,
                        color = if (mensaje == "Login exitoso") Color.Green else Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                //Boton
                BotonLevelUp(
                    "Ingresar",
                    onClickAccion = {
                        //VALIDACION PARA ACTIVAR LAS ANIMACIONES
                        if (usuario.isBlank() || contrasena.isBlank()) {
                            showError = true //ESTO ACTIVA LAS ANIMACIONES
                            mensaje = "Complete todos los campos"
                            return@BotonLevelUp
                        }

                        showError = false // Desactivar errores si todo está bien

                        CoroutineScope(Dispatchers.IO).launch {
                            val loginExitoso = viewModel.login(usuario, contrasena)

                            withContext(Dispatchers.Main) {
                                if (loginExitoso) {
                                    mensaje = "Login exitoso"
                                    navController.navigate("home/${usuario}") {  // Pasa el usuario como parámetro
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    mensaje = "Usuario o contraseña incorrectos"
                                    showError = true //Mostrar error con animación
                                }
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                //Boton registrar
                BotonLevelUp(
                    "Registrar",
                    onClickAccion = {
                        navController.navigate("registro")
                    }
                )
            }
        }
    }
}