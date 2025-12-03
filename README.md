🌿 Huerto Hogar – Aplicación Móvil + Microservicios (Auth + Productos)

Aplicación móvil desarrollada en Android Studio con Jetpack Compose, conectada a dos microservicios REST construidos en Spring Boot:

✔ Microservicio de Autenticación (AuthService) – Login, Registro, JWT

✔ Microservicio de Productos (ProductService) – CRUD completo de productos

El proyecto implementa consumo de APIs, arquitectura MVVM, navegación, ViewModels, Retrofit, pruebas unitarias y funcionalidades avanzadas exigidas por la rúbrica.

📱 1. Aplicación Android

La app permite:

Login y registro con token JWT

Catálogo de productos conectado al microservicio

Vista de detalles por producto

Carrito de compras local

Cámara funcional para fotos

Pantalla de clima (consumo de API externa OpenWeather)

Pantalla para editar producto (PUT al microservicio)

Sistema de sesión con DataStore

Navegación con NavHost + Compose

Arquitectura MVVM limpia y desacoplada

🧩 2. Microservicios del Proyecto
🔐 2.1. Microservicio de Autenticación (Auth Service)
✔ Tecnologías

Spring Boot 3

Spring Security 6

JWT (Json Web Token)

JPA + Hibernate

H2 o MySQL

BCryptPasswordEncoder

✔ Funcionalidades
Endpoint	Método	Descripción
/auth/register	POST	Registra usuarios nuevos
/auth/login	POST	Inicia sesión y retorna un JWT
/auth/me	GET	Obtiene datos del usuario autenticado
✔ Flujo de autenticación

El usuario se registra → se guarda en la BD.

El usuario hace login → se valida y se genera un JWT.

La app Android guarda el token en DataStore.

Cada request protegida incluye
Authorization: Bearer <token>

✔ Entidades importantes
User.java

id

username

password (encriptada)

role (ADMIN / USER)

Role.java

Enum de roles.

JwtService.java

Genera y valida tokens JWT.

SecurityConfig.java

Configura:

Cors

Filtros

Rutas públicas y privadas

Autenticación por token

🍏 2.2. Microservicio de Productos (Product Service)
✔ Tecnologías

Spring Boot 3

Spring Web

Spring Security

JPA / Hibernate

✔ Funcionalidades CRUD
Endpoint	Método	Descripción
/products	GET	Retorna todos los productos
/products/{id}	GET	Retorna un producto por ID
/api/productos	POST	Crear producto (ADMIN)
/api/productos/{id}	PUT	Editar producto
/api/productos/{id}	DELETE	Eliminar producto
✔ Modelo Product.java

id

nombre

descripción

precio

unidad (kg, unidad, bandeja, etc.)

imagen (ruta del archivo en /uploads o nombre del drawable)

✔ DataLoader.java

Carga 3 productos iniciales cuando se levanta el servicio.

🔗 3. Conexión Android ↔ Microservicios
🟩 API Auth → Usada para Login y Registro

En Android se usa:

AuthApi.kt

AuthRepository.kt

AuthViewModel.kt

El Login devuelve un token JWT:

{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}


Token guardado en DataStore → se usa en todas las llamadas privadas.

🟦 API Productos → Catálogo, Detalle y Edición

En Android se usa:

ProductApi.kt

ProductRepository.kt

ProductViewModel.kt

Catálogo → GET /products
Detalle → GET /products/{id}
Editar → PUT /api/productos/{id}

La pantalla EditarProductoScreen.kt envía cambios al servidor.

🌤️ 4. API Externa OpenWeather

La app consume clima usando:

WeatherApi.kt

WeatherResponse.kt

WeatherViewModel.kt

Pantalla incluida en la navegación:
✔ /clima

🧪 5. Pruebas Unitarias (JUnit + Mockito)

En ProductService se implementaron pruebas como:

✔ Test: obtener todos los productos

Mock del repositorio → se valida

estado 200

tamaño de la lista

datos del primer producto

✔ Test: obtener producto por ID

Mock del repo → se verifica

status OK

id correcto

nombre correcto

repo.findById() llamado 1 vez

🙌 6. Gestión Ágil y Kanban (GitHub Projects)

El proyecto incluye tablero Kanban con:

Backlog

To Do

In Progress

Done

Las tareas del proyecto están organizadas con GitHub Issues.

🧱 7. Estructura del Proyecto
📦 HuertoHogar/
 ├── auth-service/
 ├── product-service/
 ├── android-app/
 │    ├── network/
 │    ├── viewmodel/
 │    ├── cosadelapagina/
 │    ├── ui/
 │    └── res/

🚀 8. Cómo ejecutar el proyecto
1️⃣ Ejecutar microservicio Auth
cd auth-service
mvn spring-boot:run

2️⃣ Ejecutar microservicio Productos
cd product-service
mvn spring-boot:run

3️⃣ Ejecutar la app Android

Abrir Android Studio → Run App.

📦 9. APK Final

Generado desde Android Studio:

Build > Build Bundle(s) / APK(s) > Build APK(s)

🎯 10. Cumplimiento de la Rúbrica

✔ API interna
✔ API externa (clima)
✔ Microservicios independientes
✔ Seguridad JWT
✔ CRUD completo
✔ Cámara
✔ Carrito
✔ Pantallas funcionales
✔ Navegación avanzada
✔ Pruebas unitarias
✔ Kanban
✔ README profesional

💚 Autor

Marco Ignacio Parra Lagos
Estudiante de Ingeniería en Informática – DUOC UC
