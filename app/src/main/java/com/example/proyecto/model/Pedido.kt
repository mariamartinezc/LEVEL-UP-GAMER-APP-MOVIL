package com.example.proyecto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "pedidos")
data class Pedido (

    @PrimaryKey(autoGenerate = true)
    val pedidoId: Int = 0,
    val usuarioId: Int,
    val fecha: String,
    val estado:String = "pendiente",
    val total: Double,
    val direccion: String

    )