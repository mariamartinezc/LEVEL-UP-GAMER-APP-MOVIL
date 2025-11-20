package com.example.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.proyecto.data.LevelUpDatabase

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val database = LevelUpDatabase.getDatabase(application)
    private val usuarioDao = database.usuarioDao()

    suspend fun login(nombre: String, contrasena: String): Boolean {
        val usuarioEncontrado = usuarioDao.login(nombre, contrasena)
        return usuarioEncontrado != null
    }
}