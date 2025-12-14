package com.example.proyecto.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeGetRule = createComposeRule()

    @Test
    fun LoginScreen_muestraTitulo() {

        composeGetRule.setContent {
            LoginScreen(
                navController = rememberNavController(),
            )
        }

        //Verifica que muestra "LEVEL UP"
        composeGetRule.onNodeWithText("LEVEL UP").assertIsDisplayed()

    }
    @Test
    fun LoginScreen_muestraBotones() {
        composeGetRule.setContent {
            LoginScreen(
                navController = rememberNavController()
            )
        }

        //Botones de la app
        composeGetRule.onNodeWithText("Ingresar").assertIsDisplayed()
        composeGetRule.onNodeWithText("Registrar").assertIsDisplayed()

    }
    @Test
    fun LoginScreen_muestraCamposTexto(){
        composeGetRule.setContent {
            LoginScreen(
                navController = rememberNavController()
            )
        }
        //Campos de texto
        composeGetRule.onNodeWithText("Usuario").assertIsDisplayed()
        composeGetRule.onNodeWithText("Contraseña").assertIsDisplayed()


    }
}