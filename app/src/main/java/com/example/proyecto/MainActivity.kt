package com.example.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.proyecto.data.UsuarioDatabase
import com.example.proyecto.ui.screen.RegistroScreen
import com.example.proyecto.ui.theme.ProyectoTheme
import com.example.proyecto.viewmodel.FormularioViewMOdel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormularioApp()
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun Preview() {
    //RegistroScreen()
}

@Composable
fun FormularioApp(){
    val context = LocalContext.current

    val database = remember {
        Room.databaseBuilder(
            context,
            UsuarioDatabase::class.java,
            "usuario.db"
        ).build()
    }
    val viewModel = remember {
        FormularioViewMOdel(database.usuarioDao())
    }
    RegistroScreen(viewModel)

}