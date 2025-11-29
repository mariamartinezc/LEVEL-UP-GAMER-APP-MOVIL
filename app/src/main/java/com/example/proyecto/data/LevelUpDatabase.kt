package com.example.proyecto.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.proyecto.model.DetalleCarrito
import com.example.proyecto.model.Usuario
import com.example.proyecto.model.Producto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Usuario::class,
        Producto::class,
        DetalleCarrito::class
    ], version = 1  // Cambia a versión 2
)
abstract class LevelUpDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun productoDao(): ProductoDao
    abstract fun carritoDao(): CarritoDao

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
                        // Llamamos a insertar datos después de un delay
                        CoroutineScope(Dispatchers.IO).launch {
                            // Esperamos un poco para que la BD esté lista
                            kotlinx.coroutines.delay(1000)
                            // Obtenemos la instancia de la BD
                            val dbInstance = getDatabase(context.applicationContext)
                            insertarDatosPorDefecto(dbInstance)
                        }
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
                database = instance
                instance
            }
        }

        private suspend fun insertarDatosPorDefecto(db: LevelUpDatabase) {

            //Insertar usuarios
            val usuarioDao = db.usuarioDao()
            val usuarios = listOf(
                Usuario(nombre = "admin", contrasena = "1234", correo = "admin@levelup.cl"),
                Usuario(nombre = "cliente", contrasena = "1234", correo = "cliente@levelup.cl"),
                Usuario(nombre = "usuario2", contrasena = "1234", correo = "usuario2@levelup.cl"),
            )
            usuarios.forEach { usuarioDao.insertarUsuario(it) }
            println("Usuarios insertados: ${usuarios.size}")

            //Insertar productos
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
            println("Productos insertados: ${productos.size}")

            kotlinx.coroutines.delay(500)

            //Agregar productos al carrito del cliente
            val carritoDao = db.carritoDao()
            val cliente = usuarioDao.obtenerUsuarioPorNombre("cliente")
            val usuario2 = usuarioDao.obtenerUsuarioPorNombre("usuario2")

            //Obtener productos recién insertados
            val productosDisponibles = productoDao.obtenerProductos()
            val mouse = productosDisponibles.find { it.nombreProducto == "Mouse Gamer" }
            val auriculares = productosDisponibles.find { it.nombreProducto == "Auriculares" }
            val silla = productosDisponibles.find { it.nombreProducto == "Silla Gamer" }
            val teclado = productosDisponibles.find { it.nombreProducto == "Teclado Mecánico" }
            val monitor = productosDisponibles.find { it.nombreProducto == "Monitor 24" }

            println("Productos disponibles: ${productosDisponibles.size}")
            println("Cliente encontrado: ${cliente != null}")
            println("Usuario2 encontrado: ${usuario2 != null}")

            // Carrito para el cliente
            cliente?.let { usuario ->
                println("Configurando carrito para cliente (ID: ${usuario.id})")

                mouse?.let { producto ->
                    carritoDao.insertar(
                        DetalleCarrito(
                            usuarioId = usuario.id,
                            productoId = producto.productoId,
                            cantidad = 1
                        )
                    )
                    println("Agregado Mouse Gamer al carrito del cliente")
                }

                auriculares?.let { producto ->
                    carritoDao.insertar(
                        DetalleCarrito(
                            usuarioId = usuario.id,
                            productoId = producto.productoId,
                            cantidad = 2
                        )
                    )
                    println("Agregado Auriculares al carrito del cliente")
                }

                silla?.let { producto ->
                    carritoDao.insertar(
                        DetalleCarrito(
                            usuarioId = usuario.id,
                            productoId = producto.productoId,
                            cantidad = 1
                        )
                    )
                    println("Agregado Silla Gamer al carrito del cliente")
                }
            }

            //Carrito para usuario2 (diferentes productos)
            usuario2?.let { usuario ->
                println("Configurando carrito para usuario2 (ID: ${usuario.id})")

                teclado?.let { producto ->
                    carritoDao.insertar(
                        DetalleCarrito(
                            usuarioId = usuario.id,
                            productoId = producto.productoId,
                            cantidad = 1
                        )
                    )
                    println("Agregado Teclado Mecánico al carrito de usuario2")
                }

                monitor?.let { producto ->
                    carritoDao.insertar(
                        DetalleCarrito(
                            usuarioId = usuario.id,
                            productoId = producto.productoId,
                            cantidad = 1
                        )
                    )
                    println("Agregado Monitor 24 al carrito de usuario2")
                }
            }

        }
    }
}