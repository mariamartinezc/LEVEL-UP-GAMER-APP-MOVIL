package com.example.proyecto.model

import androidx.room.Entity

@Entity("detallePedido",
    primaryKeys = ["pedidoId","productoId"]
)
data class DetallePedido (
    val pedidoId:Int,
    val productoId:Int,
    val cantidad:Int,
    val precio:Double
)