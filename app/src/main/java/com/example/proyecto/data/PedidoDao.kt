package com.example.proyecto.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto.model.Pedido

@Dao
interface PedidoDao {
    @Insert
    suspend fun insertar(pedido: Pedido): Long

    @Update
    suspend fun actualizar(pedido: Pedido)

    @Delete
    suspend fun eliminar(pedido: Pedido)

    @Query("SELECT * FROM pedidos")
    suspend fun obtenerTodos(): List<Pedido>

    @Query("SELECT * FROM pedidos WHERE pedidoId = :id")
    suspend fun obtenerPorId(id: Int): Pedido?

    @Query("SELECT * FROM pedidos WHERE usuarioId = :usuarioId")
    suspend fun obtenerPorUsuario(usuarioId: Int): List<Pedido>
}