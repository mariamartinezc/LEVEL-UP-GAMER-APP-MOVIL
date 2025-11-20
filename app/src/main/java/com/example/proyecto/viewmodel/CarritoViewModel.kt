package com.example.proyecto.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyecto.model.Carrito
import com.example.proyecto.model.Producto

class CarritoViewModel : ViewModel() {
    private val _carrito = mutableStateListOf<Carrito>()
    val carrito: List<Carrito> get() = _carrito

    fun agregarProducto(producto: Producto, cantidad: Int = 1) {
        val existingItem = _carrito.find { it.producto.productoId == producto.productoId }
        if (existingItem != null) {
            // Actualizar cantidad si ya existe
            val index = _carrito.indexOf(existingItem)
            _carrito[index] = Carrito(producto, existingItem.cantidad + cantidad)
        } else {
            // Agregar nuevo item
            _carrito.add(Carrito(producto, cantidad))
        }
    }

    fun eliminarProducto(productoId: Int) {
        _carrito.removeAll { it.producto.productoId == productoId }
    }

    fun calcularTotal(): Double {
        return _carrito.sumOf { it.getSubtotal() }
    }
}