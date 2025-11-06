package com.example.proyecto.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyecto.model.Usuario

@Dao
interface UsuarioDao {
//influye los metodos abstractos
    @Insert//por defecto
    suspend fun insertar(usuario: Usuario)
    @Query("SELECT * FROM usuario")
    suspend fun  obtenerUsuarios():List<Usuario>
//@Query consultas personalizadas

}