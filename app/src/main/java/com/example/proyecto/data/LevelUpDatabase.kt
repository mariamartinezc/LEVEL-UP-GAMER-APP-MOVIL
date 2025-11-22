package com.example.proyecto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto.model.Usuario
import com.example.proyecto.model.Pedido
import com.example.proyecto.model.Producto
import com.example.proyecto.model.DetallePedido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Usuario::class,
        Pedido::class,
        Producto::class,
        DetallePedido::class
    ], version = 1
)
abstract class LevelUpDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun productoDao(): ProductoDao
    abstract fun detallePedidoDao(): DetallePedidoDao

    companion object {
        @Volatile
        private var database: LevelUpDatabase? = null

        fun getDatabase(context: Context): LevelUpDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LevelUpDatabase::class.java,
                    "levelup.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            insertarDatosPorDefecto(database!!)
                        }
                    }
                })
                    .build()
                database = instance
                instance
            }
        }

        private suspend fun insertarDatosPorDefecto(db: LevelUpDatabase) {
            // Insertar usuarios
            val usuarioDao = db.usuarioDao()
            val usuarios = listOf(
                Usuario(nombre = "admin", contrasena = "1234", correo = "admin@levelup.cl"),
                Usuario(nombre = "cliente", contrasena = "1234", correo = "cliente@levelup.cl"),
            )
            usuarios.forEach { usuarioDao.insertarUsuario(it) }

            // Insertar productos
            val productoDao = db.productoDao()
            val productos = listOf(
                Producto(
                    nombreProducto = "Mouse Gamer",
                    descripcion = "Mouse gaming profesional con RGB",
                    precio = 29990.0,
                    imagen = "mouse.png",
                    stock = 10,
                    categoria = "Periféricos"
                ),
                Producto(
                    nombreProducto = "Teclado Mecánico",
                    descripcion = "Teclado mecánico switches blue",
                    precio = 49990.0,
                    imagen = "teclado",
                    stock = 5,
                    categoria = "Periféricos"
                ),
                Producto(
                    nombreProducto = "Auriculares",
                    descripcion = "Auriculares gaming con sonido surround",
                    precio = 39990.0,
                    imagen = "auriculares",
                    stock = 8,
                    categoria = "Audio"
                ),
                // Puedes agregar mas productos
                Producto(
                    nombreProducto = "Monitor 24",
                    descripcion = "Monitor Full HD 144Hz",
                    precio = 199990.0,
                    imagen = "monitor",
                    stock = 3,
                    categoria = "Monitores"
                ),
                Producto(
                    nombreProducto = "Silla Gamer",
                    descripcion = "Silla ergonómica para gaming",
                    precio = 129990.0,
                    imagen = "silla.jpg",
                    stock = 4,
                    categoria = "Muebles"
                )
            )
            productos.forEach { productoDao.insertarProducto(it) }
        }
    }
}