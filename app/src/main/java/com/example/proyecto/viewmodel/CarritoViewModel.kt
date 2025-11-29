package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.LevelUpDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarritoViewModel(application: Application) : AndroidViewModel(application) {
    private val database = LevelUpDatabase.getDatabase(application)
    private val carritoDao = database.carritoDao()
    private val productoDao = database.productoDao()
    private val usuarioDao = database.usuarioDao()

    private val _carrito = MutableStateFlow<List<com.example.proyecto.model.Carrito>>(emptyList())
    val carrito: StateFlow<List<com.example.proyecto.model.Carrito>> = _carrito

    fun cargarCarrito(usuarioNombre: String) {
        viewModelScope.launch {
            val usuario = usuarioDao.obtenerUsuarioPorNombre(usuarioNombre) ?: return@launch
            val itemsCarrito = carritoDao.obtenerCarritoPorUsuario(usuario.id)

            val carritoUI = itemsCarrito.mapNotNull { detalleCarrito ->
                val producto = productoDao.obtenerPorId(detalleCarrito.productoId)
                producto?.let {
                    com.example.proyecto.model.Carrito(
                        producto = it,
                        cantidad = detalleCarrito.cantidad,
                        usuarioNombre = usuarioNombre
                    )
                }
            }

            _carrito.value = carritoUI
        }
    }

    fun agregarProducto(usuarioNombre: String, producto: com.example.proyecto.model.Producto) {
        viewModelScope.launch {
            val usuario = usuarioDao.obtenerUsuarioPorNombre(usuarioNombre) ?: return@launch
            val itemExistente = carritoDao.obtenerItem(usuario.id, producto.productoId)

            if (itemExistente != null) {
                carritoDao.actualizarCantidad(itemExistente.detalleCarritoId, itemExistente.cantidad + 1)
            } else {
                carritoDao.insertar(
                    com.example.proyecto.model.DetalleCarrito(
                        usuarioId = usuario.id,
                        productoId = producto.productoId,
                        cantidad = 1
                    )
                )
            }
            cargarCarrito(usuarioNombre)
        }
    }

    fun eliminarProducto(usuarioNombre: String, productoId: Int) {
        viewModelScope.launch {
            val usuario = usuarioDao.obtenerUsuarioPorNombre(usuarioNombre) ?: return@launch
            val item = carritoDao.obtenerItem(usuario.id, productoId)
            item?.let {
                carritoDao.eliminar(it)
                cargarCarrito(usuarioNombre)
            }
        }
    }

    fun calcularTotal(): Double {
        return _carrito.value.sumOf { it.getSubtotal() }
    }
}