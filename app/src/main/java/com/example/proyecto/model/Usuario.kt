package com.example.proyecto.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val contrasena: String,
    val correo: String = "",  // Valor por defecto
    val direccion: String? = null,  // Opcional
    val telefono: String? = null  // Opcional
)
