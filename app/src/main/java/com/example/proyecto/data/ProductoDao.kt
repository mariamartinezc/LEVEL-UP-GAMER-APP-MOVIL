package com.example.proyecto.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyecto.model.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos")
    suspend fun obtenerProductos(): List<Producto>

    @Query("SELECT * FROM productos WHERE productoId = :productoId")
    suspend fun obtenerPorId(productoId: Int): Producto?

    @Query("SELECT * FROM productos WHERE categoria = :categoria")
    suspend fun obtenerPorCategoria(categoria: String): List<Producto>

    @Query("SELECT * FROM productos WHERE activo = 1")
    suspend fun obtenerProductosActivos(): List<Producto>

    // INSERT para agregar productos
    @Insert
    suspend fun insertarProducto(producto: Producto)

    @Query("UPDATE productos SET stock = :nuevoStock WHERE productoId = :productoId")
    suspend fun actualizarStock(productoId: Int, nuevoStock: Int)

    @Query("DELETE FROM productos WHERE productoId = :productoId")
    suspend fun eliminarProducto(productoId: Int)
}