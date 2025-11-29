package com.example.proyecto.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detalleCarrito")
data class DetalleCarrito(
    @PrimaryKey(autoGenerate = true)
    val detalleCarritoId: Int = 0,
    val usuarioId: Int,
    val productoId: Int,
    val cantidad: Int
)