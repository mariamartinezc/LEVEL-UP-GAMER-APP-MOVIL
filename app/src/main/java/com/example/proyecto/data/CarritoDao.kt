package com.example.proyecto.data

import androidx.room.*
import com.example.proyecto.model.DetalleCarrito

// data/CarritoDao.kt
@Dao
interface CarritoDao {
    @Insert
    suspend fun insertar(item: DetalleCarrito): Long

    @Update
    suspend fun actualizar(item: DetalleCarrito)

    @Delete
    suspend fun eliminar(item: DetalleCarrito)

    @Query("DELETE FROM detalleCarrito WHERE detalleCarritoId = :detalleCarritoId")
    suspend fun eliminarPorId(detalleCarritoId: Int)

    @Query("DELETE FROM detalleCarrito WHERE usuarioId = :usuarioId")
    suspend fun limpiarCarrito(usuarioId: Int)

    @Query("SELECT * FROM detalleCarrito WHERE usuarioId = :usuarioId")
    suspend fun obtenerCarritoPorUsuario(usuarioId: Int): List<DetalleCarrito>

    @Query("SELECT * FROM detalleCarrito WHERE usuarioId = :usuarioId AND productoId = :productoId")
    suspend fun obtenerItem(usuarioId: Int, productoId: Int): DetalleCarrito?

    @Query("UPDATE detalleCarrito SET cantidad = :nuevaCantidad WHERE detalleCarritoId = :detalleCarritoId")
    suspend fun actualizarCantidad(detalleCarritoId: Int, nuevaCantidad: Int)
}