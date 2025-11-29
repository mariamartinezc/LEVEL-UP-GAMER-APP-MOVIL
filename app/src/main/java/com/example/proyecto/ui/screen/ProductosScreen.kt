package com.example.proyecto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.model.Producto
import com.example.proyecto.ui.components.BotonLevelUp
import com.example.proyecto.viewmodel.CarritoViewModel
import com.example.proyecto.viewmodel.ProductoViewModel

@Composable
fun ProductosScreen(navController: NavController) {
    val viewModel: ProductoViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()
    val carritoViewModel: CarritoViewModel = viewModel()
    val categorias by viewModel.categorias.collectAsState() //Obtiene las categorías del ViewModel

    val usuarioActual = "cliente"

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
        viewModel.cargarCategorias() //Cargar categorías al iniciar
        carritoViewModel.cargarCarrito(usuarioActual)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Header fijo
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Bienvenido a LevelUp Store",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A148C)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Productos gaming disponibles",
                fontSize = 18.sp,
                color = Color(0xFF757575)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
        //LAZYROW CON CATEGORÍAS DINÁMICAS
            if (categorias.isNotEmpty()) {
                Text(
                    text = "Filtrar por categoría:",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFF4A148C),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                //LazyRow: Categorías (horizontal)
                LazyRow(
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    items(categorias) { categoria ->
                        Text(
                            text = categoria,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .background(
                                    color = Color(0xFF6C2C94),
                                    shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable{
                                    if (categoria == "Todos") {
                                        viewModel.cargarProductos()
                                    } else {
                                        viewModel.cargarProductosPorCategoria(categoria)
                                    }
                                }
                        )
                    }
                }
            }

        // Lista de productos
        //LazyColumn: Productos (vertical)
        LazyColumn(
            modifier = Modifier.weight(1f)  //hace que ocupe el espacio restante
        ) {
            items(productos) { producto ->
                ProductoItem(producto = producto)
            }
        }

        // Botón fijo en la parte inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            BotonLevelUp("Volver") {
                navController.popBackStack()
                // navController.navigate("home")
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto) {
    val context = LocalContext.current
    val imageResource = obtieneImagen(context, producto.imagen)

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Imagen de ${producto.nombreProducto}",
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)
        )

        Column {
            Text(
                text = producto.nombreProducto,
                fontWeight = FontWeight.Bold
            )
            Text(text = "$${producto.precio}")
            Text(
                text = producto.descripcion,
                fontSize = 12.sp,
                color = Color.Gray
            )

        }


    }

}


private fun obtieneImagen(context: android.content.Context, imagen: String?): Int {
    val nombre = imagen?.replace(".png", "")?.replace(".jpg", "") ?: "logocircular"
    val resourceId = context.resources.getIdentifier(nombre, "drawable", context.packageName)
    return if (resourceId == 0) R.drawable.logocircular else resourceId
}