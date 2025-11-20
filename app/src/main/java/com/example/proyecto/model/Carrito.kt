package com.example.proyecto.model



data class Carrito (
    val producto: Producto,
    val cantidad: Int
){
    fun getSubtotal(): Double = producto.precio * cantidad
}