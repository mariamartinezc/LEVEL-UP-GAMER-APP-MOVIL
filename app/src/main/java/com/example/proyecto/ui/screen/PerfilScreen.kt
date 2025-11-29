package com.example.proyecto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.ui.components.BotonLevelUp
import com.example.proyecto.ui.components.InformacionCard
import com.example.proyecto.ui.components.TituloText
import com.example.proyecto.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(navController: NavController, userNombre: String) {
    val viewModel: PerfilViewModel = viewModel()
    var usuario by remember { mutableStateOf<com.example.proyecto.model.Usuario?>(null) }

    // Cargar datos del usuario
    LaunchedEffect(userNombre) {
        usuario = viewModel.cargarUsuario(userNombre)
    }
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
                .alpha(0.8f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) ,
                verticalArrangement = Arrangement.Center,
        ) {
            //Título
            TituloText("Mi Perfil,")

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = userNombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                letterSpacing = 1.sp
            )

            // Información del usuario
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    InformacionCard("Nombre de usuario", userNombre)
                    InformacionCard("Correo electrónico", usuario?.correo ?: "No especificado")
                    InformacionCard("Teléfono", usuario?.telefono ?: "No especificado")
                    InformacionCard("Dirección", usuario?.direccion ?: "No especificado")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BotonLevelUp(
                    texto = "Editar Perfil",
                    onClickAccion = {
                        navController.navigate("editarPerfil/${userNombre}")
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                BotonLevelUp(
                    texto = "Volver al Home",
                    onClickAccion = {
                        navController.popBackStack()
                        //navController.navigate("home")
                    }
                )
            }
        }
    }
}

