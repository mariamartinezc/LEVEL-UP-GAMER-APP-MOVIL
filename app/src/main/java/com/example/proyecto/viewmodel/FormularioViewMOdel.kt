package com.example.proyecto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.data.UsuarioDao
import com.example.proyecto.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FormularioViewMOdel (private  val usuarioDao: UsuarioDao): ViewModel(){
    private val _usuario = MutableStateFlow<List<Usuario>>(emptyList())


    fun agregarUsuario(nombre: String ,contrasena: String){
        val nuevoUsuario = Usuario(nombre=nombre, contrasena = contrasena)

        viewModelScope.launch {
            usuarioDao.insertar(nuevoUsuario)
            _usuario.value = usuarioDao.obtenerUsuarios()
        }
    }

    fun cargarUsuarios(){
        viewModelScope.launch {
            _usuario.value = usuarioDao.obtenerUsuarios()
        }
    }
}