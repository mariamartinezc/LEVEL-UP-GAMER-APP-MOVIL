package com.example.proyecto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.ui.components.BotonLevelUp
import com.example.proyecto.ui.components.TituloText

@Composable
fun HomeScreen(navController: NavController, userNombre: String? = null) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Fondo LevelUp home",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.9f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //Logo más pequeño
            Image(
                painter = painterResource(id = R.drawable.level),
                contentDescription = "Logo LevelUp",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Título
            TituloText("Bienvenido,")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = userNombre ?: "Usuario",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
            //Primera fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    BotonLevelUp(
                        texto = "Crear Producto",
                        onClickAccion = {
                            navController.navigate("registroProducto")
                        }
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    BotonLevelUp(
                        texto = "Ver Productos",
                        onClickAccion = {
                            navController.navigate("productos")
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            //Segunda fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    BotonLevelUp(
                        texto = "Mi Perfil",
                        onClickAccion = {
                            navController.navigate("perfil/${userNombre ?: ""}")
                        }
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    BotonLevelUp(
                        texto = "Mi Carrito",
                            onClickAccion = {
                                // Pasa el nombre del usuario actual al carrito
                                val usuarioActual = userNombre ?: "cliente"
                                navController.navigate("carrito/$usuarioActual")

                        }
                    )
                }
            }

            //Boton Cerrar Sesion
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BotonLevelUp(
                    texto = "Cerrar Sesión",
                    onClickAccion = {
                        navController.navigate("login")
                    }
                )
            }
        }
    }
}

