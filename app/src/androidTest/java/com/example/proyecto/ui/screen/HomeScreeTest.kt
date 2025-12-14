package com.example.proyecto.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class HomeScreeTest {

    @get:Rule
    val composeGetRule = createComposeRule()

    @Test
    fun HomeScreen_muestraTitulo() {
        val nombreUsuario = "admin"

        composeGetRule.setContent {
            HomeScreen(
                navController = rememberNavController(),
                userNombre = nombreUsuario
            )
        }

        //Verifica que muestra "Bienvenido,"
        composeGetRule.onNodeWithText("Bienvenido,").assertIsDisplayed()

        //verifica que muestra el nombre del usuario
        composeGetRule.onNodeWithText(nombreUsuario).assertIsDisplayed()
    }

    @Test
    fun HomeScreen_muestraBotones() {
        val nombreUsuario = "cliente"
        composeGetRule.setContent {
            HomeScreen(
                navController = rememberNavController(),
            )
        }

        //Botones de la app
        composeGetRule.onNodeWithText("Crear Producto").assertIsDisplayed()
        composeGetRule.onNodeWithText("Ver Productos").assertIsDisplayed()
        composeGetRule.onNodeWithText("Mi Perfil").assertIsDisplayed()
        composeGetRule.onNodeWithText("Mi Carrito").assertIsDisplayed()
        composeGetRule.onNodeWithText("Cerrar Sesión").assertIsDisplayed()
    }


}
