package com.example.proyecto.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyecto.model.Usuario

@Database(entities = [Usuario::class], version = 1)//poner mas entidades de level up
abstract class UsuarioDatabase : RoomDatabase() {
    abstract  fun usuarioDao(): UsuarioDao
}