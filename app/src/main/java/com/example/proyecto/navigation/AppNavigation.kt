package com.example.proyecto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.ui.screen.CarritoScreen
import com.example.proyecto.ui.screen.EditarPerfilScreen
import com.example.proyecto.ui.screen.HomeScreen
import com.example.proyecto.ui.screen.LoginScreen
import com.example.proyecto.ui.screen.PerfilScreen
import com.example.proyecto.ui.screen.ProductosScreen
import com.example.proyecto.ui.screen.RegistroProductoScreen
import com.example.proyecto.ui.screen.RegistroScreen
import com.example.proyecto.ui.screen.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController = navController) }
        composable("login") { LoginScreen(navController = navController) }

        composable("home/{userNombre}") { backStackEntry ->
            val userNombre = backStackEntry.arguments?.getString("userNombre") ?: "Usuario"
            HomeScreen(
                navController = navController,
                userNombre = userNombre
            )
        }

        composable("productos") { ProductosScreen(navController = navController) }
        composable("registroProducto") { RegistroProductoScreen(navController = navController) }
        composable("registro") { RegistroScreen(navController = navController) }

        // MODIFICAR: Cambiar a userNombre temporalmente
        composable("editarPerfil/{userNombre}") { backStackEntry ->
            val userNombre = backStackEntry.arguments?.getString("userNombre") ?: ""
            EditarPerfilScreen(
                navController = navController,
                userNombre = userNombre
            )
        }

        composable("perfil/{userNombre}") { backStackEntry ->
            val userNombre = backStackEntry.arguments?.getString("userNombre") ?: ""
            PerfilScreen(navController = navController, userNombre = userNombre)
        }

        composable("carrito/{userNombre}") { backStackEntry ->
            val userNombre = backStackEntry.arguments?.getString("userNombre") ?: "Usuario"
            CarritoScreen(
                navController = navController,
                userNombre = userNombre
            )
        }
    }
}