# Level Up - Gamer Store

Una aplicación móvil de e-commerce especializada en productos gaming desarrollada en Kotlin con Jetpack Compose.

## Descripción del Proyecto

Level Up es una tienda virtual móvil diseñada específicamente para entusiastas del gaming. La aplicación permite a los usuarios explorar productos gaming, gestionar su carrito de compras y administrar su perfil de usuario de manera intuitiva y atractiva.

## Integrantes del Proyecto

- Maria Martinez
- Lorena Figueroa

## Funcionalidades Implementadas

### Autenticación y Usuarios
- Splash Screen - Pantalla de inicio con logo
- Login - Autenticación de usuarios existentes
- Registro - Creación de nuevas cuentas
- Perfil de Usuario - Visualización de datos personales
- Edición de Perfil - Modificación de información de usuario

### Catálogo y Productos
- Home Screen - Dashboard principal con navegación
- Catálogo de Productos - Lista de productos disponibles
- Filtros por Categoría - Navegación dinámica por categorías
- Registro de Productos - Formulario para agregar nuevos productos
- Imágenes de Productos - Visualización de imágenes asociadas

### Carrito de Compras
- Visualizar productos ya seleccionados por el usuario
- Carrito por Usuario - Sesión individual por usuario

### Experiencia de Usuario
- Interfaz Gaming - Diseño temático con colores gaming
- Animaciones - Transiciones y feedback visual
- Validaciones - Verificación en tiempo real de formularios
- Navegación Fluida - Transiciones entre pantallas
- Componentes Reutilizables - Design system consistente

## Tecnologías y Arquitectura

### Arquitectura
- MVVM (Model-View-ViewModel) - Separación clara de responsabilidades
- Jetpack Compose - UI declarativa moderna
- Room Database - Persistencia local de datos
- Navigation Component - Gestión de flujo entre pantallas

### Tecnologías Implementadas
- Kotlin - Lenguaje de programación
- Jetpack Compose - Framework de UI
- Room - Base de datos local
- Corrutinas - Programación asíncrona
- StateFlow - Manejo de estado reactivo
- Material Design 3 - Sistema de diseño

### Patrones de Diseño
- Repository Pattern (a través de DAOs)
- Observer Pattern (StateFlow)
- Factory Pattern (Database)
- Component Pattern (UI reutilizable)

## Estructura del Proyecto
app/<br>
├── data/<br>
│   ├── LevelUpDatabase.kt<br>
│   ├── CarritoDao.kt<br>
│   ├── ProductoDao.kt<br>
│   └── UsuarioDao.kt<br>
├── model/<br>
│   ├── Usuario.kt<br>
│   ├── Producto.kt<br>
│   ├── DetalleCarrito.kt<br>
│   └── Carrito.kt<br>
├── viewmodel/<br>
│   ├── CarritoViewModel.kt<br>
│   ├── LoginViewModel.kt<br>
│   ├── PerfilViewModel.kt<br>
│   └── ProductoViewModel.kt<br>
├── ui/<br>
│   ├── screen/<br>
│   └── components/<br>
└── navigation/<br>
    └── AppNavigation.kt<br>

## Base de Datos

### Entidades
- Usuario - Información de usuarios registrados
- Producto - Catálogo de productos gaming
- DetalleCarrito - Relación usuario-producto en carrito

### Datos de Prueba Incluidos
- 3 Usuarios: admin, cliente, usuario2 (contraseña: 1234)
- 5 Productos: Mouse Gamer, Teclado Mecánico, Auriculares, Monitor 24", Silla Gamer
- Carritos Pre-configurados para usuario cliente y usuario2

## Credenciales de Prueba

**Usuarios Disponibles:**
- Usuario: admin, Contraseña: 1234
- Usuario: cliente, Contraseña: 1234
- Usuario: usuario2, Contraseña: 1234

## Flujo de Uso Recomendado

1. Inicio: La aplicación inicia con Splash Screen
2. Login: Usar credenciales de prueba (cliente/1234)
3. Explorar: Navegar por el catálogo de productos
4. Carrito: Ver productos seleccionados y cálculos de totales
5. Perfil: Ver y editar información personal
6. Registro: Probar creación de nuevo usuario

## Personalización y Extensión

### Agregar Nuevos Productos
Desde la pantalla Home -> "Crear Producto" se pueden agregar nuevos items al catálogo.

### Modificar Categorías
Las categorías se generan automáticamente desde los productos existentes en la base de datos.

## Características Técnicas Destacables

- 100% Kotlin con Jetpack Compose
- Base de datos local con Room
- Arquitectura MVVM con StateFlow
- Navegación type-safe con Compose Navigation
- Design system con componentes reutilizables
- Manejo de estado reactivo y asíncrono



