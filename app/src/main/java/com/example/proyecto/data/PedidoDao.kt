package com.example.proyecto.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.proyecto.model.Pedido

@Dao
interface PedidoDao {
    @Insert
    suspend fun insertar(pedido: Pedido): Long
}