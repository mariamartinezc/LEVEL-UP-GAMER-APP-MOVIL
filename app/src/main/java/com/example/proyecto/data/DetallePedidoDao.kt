package com.example.proyecto.data

import androidx.room.*
import com.example.proyecto.model.DetallePedido

@Dao
interface DetallePedidoDao {
    @Insert
    suspend fun insertar(detalle: DetallePedido)

    @Update
    suspend fun actualizar(detalle: DetallePedido)

    @Delete
    suspend fun eliminar(detalle: DetallePedido)

    @Query("SELECT * FROM detallePedido")
    suspend fun obtenerTodos(): List<DetallePedido>

    @Query("SELECT * FROM detallePedido WHERE pedidoId = :pedidoId")
    suspend fun obtenerPorPedido(pedidoId: Int): List<DetallePedido>
}