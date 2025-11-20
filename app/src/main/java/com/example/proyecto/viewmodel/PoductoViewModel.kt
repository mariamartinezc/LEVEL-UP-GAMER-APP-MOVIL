package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.LevelUpDatabase
import com.example.proyecto.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application): AndroidViewModel(application) {
    private val database = LevelUpDatabase.getDatabase(application)
    private val productoDao = database.productoDao()

    private val _productos = MutableStateFlow(emptyList<Producto>())
    val productos: StateFlow<List<Producto>> = _productos

    fun cargarProductos() {
        viewModelScope.launch {
            val lista = productoDao.obtenerProductosActivos()
            _productos.value = lista
        }
    }

    fun cargarProductosPorCategoria(categoria: String) {
        viewModelScope.launch {
            val lista = productoDao.obtenerPorCategoria(categoria)
            _productos.value = lista
        }
    }
}