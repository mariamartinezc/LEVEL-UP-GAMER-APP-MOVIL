package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.proyecto.data.LevelUpDatabase
import com.example.proyecto.model.Usuario

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val database = LevelUpDatabase.getDatabase(application)
    private val usuarioDao = database.usuarioDao()

    suspend fun login(nombre: String, contrasena: String): Boolean {
        val usuarioEncontrado = usuarioDao.login(nombre, contrasena)
        return usuarioEncontrado != null
    }
    suspend fun registroUsuario(usuario: Usuario): Boolean {
        return try {
            // Verificar si el usuario ya existe
            val usuarioExistente = usuarioDao.obtenerUsuarioPorNombre(usuario.nombre)
            if (usuarioExistente != null) {
                // Usuario ya existe
                return false
            }

            // Insertar nuevo usuario
            usuarioDao.insertarUsuario(usuario)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}