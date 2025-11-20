package com.example.proyecto.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.proyecto.viewmodel.ProductoViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: ProductoViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
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

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(productos) { producto ->
                ProductoItem(producto = producto)
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
    val nombre = imagen?.replace(".png", "") ?: "logocircular"
    val resourceId = context.resources.getIdentifier(nombre, "drawable", context.packageName)
    return if (resourceId == 0) R.drawable.logocircular else resourceId
}